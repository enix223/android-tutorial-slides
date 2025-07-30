package com.enixyu.fileoperationdemo.db;

import java.util.List;

public class SharedPrefDataProvider implements DataProvider {

  @Override
  public List<Todo> read() {
    return List.of();
  }

  @Override
  public void write(Todo item) {

  }
}
