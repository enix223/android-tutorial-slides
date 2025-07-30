package com.enixyu.fileoperationdemo.db;

public interface DataProvider {

  String read();

  void write(String content);
}
