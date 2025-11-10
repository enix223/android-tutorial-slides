package com.enixyu.mqttcontroller.controller;

import android.content.Context;
import android.util.Log;
import com.alibaba.fastjson2.JSON;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.model.Command;
import com.enixyu.mqttcontroller.model.PropertiesChangeRequest;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.datatypes.MqttTopic;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @noinspection FieldCanBeLocal
 */
public class MqttRemoteController implements RemoteController {

  private final String TAG = getClass().getSimpleName();
  private final String UPSTREAM_TOPIC = "eiot/%s/properties/upstream";
  private final String DOWNSTREAM_TOPIC = "eiot/%s/properties/downstream";
  private final String DOWNSTREAM_TOPIC_REGEXP = "^eiot/(\\w+)/properties/downstream$";
  private Context mContext;

  private OnDevicePropertiesChangedListener mOnDevicePropertiesChangedListener;
  private OnConnectionChangedListener mOnDeviceConnectionChangedListener;

  private Mqtt3AsyncClient mClient;

  @Override
  public void connect(Context context, ConnectOptions options) throws RemoteControlException {
    mContext = context;
    if (mClient != null) {
      disconnect();
    }

    mOnDeviceConnectionChangedListener = options.getOnDeviceConnectionChangedListener();
    mOnDevicePropertiesChangedListener = options.getOnDevicePropertiesChangedListener();

    mClient = MqttClient.builder()
        .addConnectedListener(context1 -> {
          Log.d(TAG, "连接服务器成功");
          if (mOnDeviceConnectionChangedListener != null) {
            mOnDeviceConnectionChangedListener.onConnectionChanged(true);
          }
        })
        .addDisconnectedListener(context2 -> {
          Log.e(TAG, "连接服务器断开");
          if (mOnDeviceConnectionChangedListener != null) {
            mOnDeviceConnectionChangedListener.onConnectionChanged(false);
          }
        })
        .identifier(getClientId())
        .serverHost(options.getBrokerHost())
        .serverPort(options.getBrokerPort())
        .useMqttVersion3()
        .automaticReconnect()
        .applyAutomaticReconnect()
        .buildAsync();
    mClient.connectWith()
        .simpleAuth()
        .username(options.getUsername())
        .password(options.getPassword().getBytes(StandardCharsets.UTF_8))
        .applySimpleAuth()
        .cleanSession(true)
        .keepAlive(30)
        .send()
        .whenComplete(((mqtt3ConnAck, throwable) -> {
          Log.d(TAG, String.format("连接结果: %s", throwable == null ? "成功" : "失败"));
          if (mOnDeviceConnectionChangedListener != null) {
            mOnDeviceConnectionChangedListener.onConnectionChanged(throwable == null);
          }
        }));
  }

  @Override
  public void disconnect() {
    if (mClient == null) {
      return;
    }
    mClient.disconnect();
    mClient = null;
  }

  @Override
  public void subscribe(String deviceId) throws RemoteControlException {
    if (mClient == null) {
      throw new RemoteControlException(mContext.getString(R.string.mqtt_not_connect));
    }
    mClient.subscribeWith()
        .topicFilter(getDownstreamTopic(deviceId))
        .qos(MqttQos.AT_LEAST_ONCE)
        .callback(mqtt3Publish -> {
          MqttTopic topic = mqtt3Publish.getTopic();
          Optional<ByteBuffer> payload = mqtt3Publish.getPayload();
          String message = payload.map(
                  byteBuffer -> StandardCharsets.UTF_8.decode(byteBuffer).toString())
              .orElse("null");
          Log.d(TAG, String.format("接收到mqtt数据: topic = %s, message = %s", topic, message));
          if (mOnDevicePropertiesChangedListener == null) {
            return;
          }

          String topicString = StandardCharsets.UTF_8.decode(topic.toByteBuffer()).toString();
          Pattern pattern = Pattern.compile(DOWNSTREAM_TOPIC_REGEXP);
          Matcher matcher = pattern.matcher(topicString);
          if (!matcher.matches()) {
            return;
          }
          String deviceId1 = matcher.group(0);

          PropertiesChangeRequest request = JSON.parseObject(message,
              PropertiesChangeRequest.class);
          if (request == null) {
            Log.w(TAG, "解析下行数据失败");
            return;
          }
          mOnDevicePropertiesChangedListener.onPropertiesChanged(deviceId1, request.getPayload());
        })
        .send()
        .whenComplete((mqtt3SubAck, throwable) -> {
          Log.d(TAG, String.format("订阅结果: %s", throwable == null ? "成功" : "失败"));
        });
  }

  @Override
  public void setProperties(String deviceId, Map<String, Object> properties)
      throws RemoteControlException {
    if (mClient == null) {
      throw new RemoteControlException(mContext.getString(R.string.mqtt_not_connect));
    }
    PropertiesChangeRequest request = new PropertiesChangeRequest(Command.SET_PROPERTIES,
        properties);
    byte[] payload = JSON.toJSONBytes(request);
    mClient.publishWith()
        .topic(getUpstreamTopic(deviceId))
        .payload(payload)
        .qos(MqttQos.AT_LEAST_ONCE)
        .send()
        .thenAccept(mqtt3Publish -> Log.e(TAG, "上报属性成功"));
  }

  private String getClientId() {
    return String.format("eiot-android-%s", UUID.randomUUID().toString());
  }

  private String getUpstreamTopic(String deviceId) {
    return String.format(UPSTREAM_TOPIC, deviceId);
  }

  private String getDownstreamTopic(String deviceId) {
    return String.format(DOWNSTREAM_TOPIC, deviceId);
  }
}
