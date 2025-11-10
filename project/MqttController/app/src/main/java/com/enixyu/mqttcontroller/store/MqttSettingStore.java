package com.enixyu.mqttcontroller.store;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.mqttcontroller.model.MqttSettingModel;

public interface MqttSettingStore {

  MqttSettingModel get(@NonNull Context context);

  void save(@NonNull Context context, @NonNull MqttSettingModel model) throws StoreException;
}
