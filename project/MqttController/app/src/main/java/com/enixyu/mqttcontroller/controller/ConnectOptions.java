package com.enixyu.mqttcontroller.controller;

import androidx.annotation.Nullable;

public class ConnectOptions {

  private final String username;

  private final String password;

  private final String brokerHost;

  private final int brokerPort;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getBrokerHost() {
    return brokerHost;
  }

  @Nullable
  private final OnConnectionChangedListener onDeviceConnectionChangedListener;

  @Nullable
  private final OnDevicePropertiesChangedListener onDevicePropertiesChangedListener;

  public ConnectOptions(String username, String password, String brokerHost, int brokerPort,
      @Nullable OnConnectionChangedListener onDeviceConnectionChangedListener,
      @Nullable OnDevicePropertiesChangedListener onDevicePropertiesChangedListener) {
    this.username = username;
    this.password = password;
    this.brokerHost = brokerHost;
    this.brokerPort = brokerPort;
    this.onDeviceConnectionChangedListener = onDeviceConnectionChangedListener;
    this.onDevicePropertiesChangedListener = onDevicePropertiesChangedListener;
  }

  @Nullable
  public OnConnectionChangedListener getOnDeviceConnectionChangedListener() {
    return onDeviceConnectionChangedListener;
  }

  @Nullable
  public OnDevicePropertiesChangedListener getOnDevicePropertiesChangedListener() {
    return onDevicePropertiesChangedListener;
  }

  public int getBrokerPort() {
    return brokerPort;
  }
}
