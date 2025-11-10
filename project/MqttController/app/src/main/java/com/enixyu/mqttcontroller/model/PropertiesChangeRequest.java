package com.enixyu.mqttcontroller.model;

import java.util.Map;

public class PropertiesChangeRequest extends MqttRequest<Map<String, Object>> {

  public PropertiesChangeRequest(Command command, Map<String, Object> payload) {
    super(command, payload);
  }
}
