package com.enixyu.mqttcontroller.controller;

public class RemoteControlException extends Exception {

  public RemoteControlException(String message) {
    super(message);
  }

  public RemoteControlException(String message, Throwable cause) {
    super(message, cause);
  }
}
