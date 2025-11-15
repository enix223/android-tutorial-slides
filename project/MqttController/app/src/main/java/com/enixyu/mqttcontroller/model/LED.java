package com.enixyu.mqttcontroller.model;

public class LED {

  private int row;

  private int column;

  private int color;

  public LED(int row, int column, int color) {
    this.row = row;
    this.column = column;
    this.color = color;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getId() {
    return row * column;
  }
}
