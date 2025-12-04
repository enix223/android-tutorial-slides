package com.enixyu.mqttcontroller.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import com.alibaba.fastjson2.JSON;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.model.MqttSettingModel;

public class MqttSettingStoreSharedPrefImpl implements MqttSettingStore {

  private final String KEY_MQTT_SETTINGS = "mqtt.settings";

  @Override
  public MqttSettingModel get(@NonNull Context context) {
    SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(context);
    String value = sf.getString(KEY_MQTT_SETTINGS, "");
    MqttSettingModel setting = JSON.parseObject(value, MqttSettingModel.class);
    if (setting == null) {
      return new MqttSettingModel();
    }
    return setting;
  }

  @Override
  public void save(@NonNull Context context, @NonNull MqttSettingModel model)
      throws StoreException {
    SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(context);
    String json = JSON.toJSONString(model);
    if (json == null || TextUtils.isEmpty(json)) {
      throw new StoreException(context.getString(R.string.serialize_failed));
    }
    sf.edit().putString(KEY_MQTT_SETTINGS, json).apply();
  }
}
