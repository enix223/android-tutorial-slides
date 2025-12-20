package com.enixyu.cookbook.model;

public class Weather {

  /// 温度
  public String temperature;

  /// 湿度
  public String humidity;

  /// 风力
  public String windSpeed;

  public Weather(String temperature, String humidity, String windSpeed) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.windSpeed = windSpeed;
  }
}
