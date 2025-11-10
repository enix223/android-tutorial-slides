package com.enixyu.mqttcontroller.model;

public class RGB {

  private byte red;
  private byte green;
  private byte blue;

  public RGB() {
  }

  public RGB(byte red, byte green, byte blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public byte getRed() {
    return red;
  }

  public void setRed(byte red) {
    this.red = red;
  }

  public byte getGreen() {
    return green;
  }

  public void setGreen(byte green) {
    this.green = green;
  }

  public byte getBlue() {
    return blue;
  }

  public void setBlue(byte blue) {
    this.blue = blue;
  }

  public static RGB fromInt(int value) {
    return new RGB((byte) ((value >> 16) & 0xff), (byte) ((value >> 8) & 0xff),
        (byte) (value & 0xff));
  }

  public String toHexFormat() {
    return String.format("%2X%2X%2X", red, green, blue);
  }
}
