package com.enixyu.fileoperationdemo.db;

import java.io.IOException;
import java.util.List;

public interface DataProvider {

  List<Todo> read() throws IOException;

  void create(Todo item) throws IOException;

  void update(Todo item) throws IOException;
}
