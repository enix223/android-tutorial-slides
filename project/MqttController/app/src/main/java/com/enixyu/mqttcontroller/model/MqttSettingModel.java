package com.enixyu.mqttcontroller.model;

public class MqttSettingModel {

  private String username;
  private String password;
  private String brokerHost;
  private int brokerPort;
  private String deviceId;

  public MqttSettingModel() {
    brokerHost = "broker.emqx.io";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
    this.brokerPort = 1883;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBrokerHost() {
    return brokerHost;
  }

  public void setBrokerHost(String brokerHost) {
    this.brokerHost = brokerHost;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public int getBrokerPort() {
    return brokerPort;
  }

  public void setBrokerPort(int brokerPort) {
    this.brokerPort = brokerPort;
  }
}
