package com.enixyu.mqttcontroller;

import com.enixyu.mqttcontroller.controller.MqttRemoteController;
import com.enixyu.mqttcontroller.controller.RemoteController;
import com.enixyu.mqttcontroller.store.MqttSettingStore;
import com.enixyu.mqttcontroller.store.MqttSettingStoreSharedPrefImpl;

public class ServiceProvider {

  public final static ServiceProvider INSTANCE = new ServiceProvider();

  public final RemoteController remoteController;
  public final MqttSettingStore mqttSettingStore;

  public ServiceProvider() {
    remoteController = new MqttRemoteController();
    mqttSettingStore = new MqttSettingStoreSharedPrefImpl();
  }
}
