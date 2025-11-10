package com.enixyu.mqttcontroller.model;

import android.graphics.Color;

public class ARGB {

  private short red;
  private short green;
  private short blue;

  public ARGB() {
  }

  public ARGB(short red, short green, short blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public short getRed() {
    return red;
  }

  public void setRed(short red) {
    this.red = red;
  }

  public short getGreen() {
    return green;
  }

  public void setGreen(short green) {
    this.green = green;
  }

  public short getBlue() {
    return blue;
  }

  public void setBlue(short blue) {
    this.blue = blue;
  }

  public static ARGB fromInt(int value) {
    return new ARGB((short) Color.red(value), (short) Color.green(value), (short) Color.blue(value));
  }

  public String toHexFormat() {
    return String.format("%2X%2X%2X", red, green, blue);
  }
}
