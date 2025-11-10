package com.enixyu.mqttcontroller.controller;

import android.content.Context;
import java.util.Map;

public interface RemoteController {

  /// 连接设备
  void connect(Context context, ConnectOptions options) throws RemoteControlException;

  /// 断开连接
  void disconnect() throws RemoteControlException;

  /// 订阅设备消息
  void subscribe(String deviceId) throws RemoteControlException;

  /// 下发设置设备的属性
  void setProperties(String deviceId, Map<String, Object> properties) throws RemoteControlException;
}
