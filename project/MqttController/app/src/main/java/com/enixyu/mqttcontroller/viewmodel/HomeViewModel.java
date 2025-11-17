package com.enixyu.mqttcontroller.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.ServiceProvider;
import com.enixyu.mqttcontroller.controller.ConnectOptions;
import com.enixyu.mqttcontroller.controller.OnConnectionChangedListener;
import com.enixyu.mqttcontroller.controller.OnDevicePropertiesChangedListener;
import com.enixyu.mqttcontroller.controller.RemoteControlException;
import com.enixyu.mqttcontroller.controller.RemoteController;
import com.enixyu.mqttcontroller.model.LED;
import com.enixyu.mqttcontroller.model.MqttSettingModel;
import com.enixyu.mqttcontroller.store.MqttSettingStore;
import com.enixyu.mqttcontroller.ui.AlertHelper;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeViewModel implements OnDevicePropertiesChangedListener,
    OnConnectionChangedListener {

  private final String TAG = getClass().getSimpleName();
  private final Context mContext;
  private final BehaviorSubject<Integer> mSelectedLight;
  private final BehaviorSubject<Color> mColor;
  private final BehaviorSubject<Integer> mBrightness;
  private final RemoteController mRemoteController;
  private final MqttSettingStore mqttSettingStore;
  private final List<LED> ledLists = new ArrayList<>();
  private final BehaviorSubject<Boolean> mConnected;

  public HomeViewModel(Context mContext) {
    this.mContext = mContext;
    mSelectedLight = BehaviorSubject.createDefault(1);
    mColor = BehaviorSubject.createDefault(Color.valueOf(Color.BLACK));
    mBrightness = BehaviorSubject.createDefault(100);
    mConnected = BehaviorSubject.createDefault(false);
    this.mRemoteController = ServiceProvider.INSTANCE.remoteController;
    this.mqttSettingStore = ServiceProvider.INSTANCE.mqttSettingStore;
    int row = 8;
    int col = 8;
    for (int i = 0; i < row * col; i++) {
      ledLists.add(new LED(i, 100, Color.valueOf(Color.RED)));
    }
  }

  public Observable<Boolean> getConnected() {
    return mConnected;
  }

  public List<LED> getLedLists() {
    return ledLists;
  }

  public Observable<Integer> getBrightness() {
    return mBrightness;
  }

  public Observable<Color> getColor() {
    return mColor;
  }

  public Observable<Integer> getSelectedLight() {
    return mSelectedLight;
  }

  public void onConnectClick() {
    if (mConnected.getValue()) {
      disconnect();
    } else {
      connect();
    }
  }

  private void connect() {
    MqttSettingModel setting = mqttSettingStore.get(mContext);
    if (TextUtils.isEmpty(setting.getDeviceId())) {
      AlertHelper.showToast(mContext, R.string.mqtt_device_id_missing);
      return;
    }
    ConnectOptions opts = new ConnectOptions(setting.getUsername(), setting.getPassword(),
        setting.getBrokerHost(), setting.getBrokerPort(), this, this);
    try {
      mRemoteController.connect(mContext, opts);
    } catch (RemoteControlException e) {
      AlertHelper.showToast(mContext, e.getMessage());
    }
  }

  private void disconnect() {
    try {
      mRemoteController.disconnect();
    } catch (RemoteControlException e) {
      AlertHelper.showToast(mContext, e.getMessage());
    }
  }

  public void setSelectedLed(int i) {
    mSelectedLight.onNext(i);
  }

  public void setLightColor(Color color) {
    Log.d(TAG, String.format("选择颜色变化: %s", String.format("%8x", color.toArgb())));
    setLightColor(color, mBrightness.getValue(), mSelectedLight.getValue());
    mColor.onNext(color);
  }

  public void setBrightness(int value) {
    Log.d(TAG, String.format("修改亮度: %d", value));
    setLightColor(mColor.getValue(), value, mSelectedLight.getValue());
    mBrightness.onNext(value);
  }

  private void setLightColor(Color color, int brightness, int id) {
    if (!mConnected.getValue()) {
      AlertHelper.showToast(mContext, R.string.mqtt_not_connect);
      return;
    }
    MqttSettingModel setting = mqttSettingStore.get(mContext);
    try {
      mRemoteController.setProperties(setting.getDeviceId(), Map.of(
          "i", id,
          "a", brightness,
          "r", (int) (color.red() * 255),
          "g", (int) (color.green() * 255),
          "b", (int) (color.blue() * 255))
      );
    } catch (RemoteControlException e) {
      Log.e(TAG, "设置颜色失败", e);
      AlertHelper.showToast(mContext, e.getMessage());
    }
  }

  @Override
  public void onConnectionChanged(boolean connected) {
    mConnected.onNext(connected);
    MqttSettingModel setting = mqttSettingStore.get(mContext);
    if (connected && !TextUtils.isEmpty(setting.getDeviceId())) {
      try {
        mRemoteController.subscribe(setting.getDeviceId());
      } catch (RemoteControlException e) {
        Log.e(TAG, "订阅失败", e);
        AlertHelper.showToast(mContext, e.getMessage());
      }
    }
  }

  @Override
  public void onPropertiesChanged(String deviceId, Map<String, Object> properties) {

  }
}
