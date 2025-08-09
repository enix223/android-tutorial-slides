package com.enixyu.fileoperationdemo.db;

import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public interface DataProvider {

  @Nullable
  Todo read() throws IOException;

  void update(Todo item) throws IOException;
}
