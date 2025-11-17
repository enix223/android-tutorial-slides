package com.enixyu.mqttcontroller.model;

import android.graphics.Color;

public class LED {

  private int index;

  private int brightness;

  private Color color;

  public LED(int index, int brightness, Color color) {
    this.index = index;
    this.brightness = brightness;
    this.color = color;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getBrightness() {
    return brightness;
  }

  public void setBrightness(int brightness) {
    this.brightness = brightness;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
