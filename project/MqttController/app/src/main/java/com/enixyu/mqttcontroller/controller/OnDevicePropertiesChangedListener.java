package com.enixyu.mqttcontroller.controller;

import java.util.Map;

public interface OnDevicePropertiesChangedListener {

  void onPropertiesChanged(String deviceId, Map<String, Object> properties);
}
