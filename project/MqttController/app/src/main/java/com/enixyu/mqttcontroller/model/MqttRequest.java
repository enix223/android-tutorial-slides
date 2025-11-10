package com.enixyu.mqttcontroller.model;

public class MqttRequest<T> {

  private final Command command;

  private final T payload;

  public MqttRequest(Command command, T payload) {
    this.command = command;
    this.payload = payload;
  }

  public T getPayload() {
    return payload;
  }

  public Command getCommand() {
    return command;
  }
}
