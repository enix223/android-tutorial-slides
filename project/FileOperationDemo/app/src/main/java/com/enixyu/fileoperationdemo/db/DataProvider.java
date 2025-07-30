package com.enixyu.fileoperationdemo.db;

import java.io.IOException;
import java.util.List;

public interface DataProvider {

  List<Todo> read() throws IOException;

  void write(Todo item) throws IOException;
}
