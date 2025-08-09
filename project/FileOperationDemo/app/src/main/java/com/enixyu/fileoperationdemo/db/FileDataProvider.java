package com.enixyu.fileoperationdemo.db;

import android.content.Context;
import android.os.Parcel;
import androidx.annotation.Nullable;
import com.alibaba.fastjson2.JSONB.IO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDataProvider implements DataProvider {

  private final String DB_FILE = "db.fd";
  private final Context context;

  public FileDataProvider(Context context) {
    this.context = context;
  }

  @Nullable
  @Override
  public Todo read() throws IOException {
    var parcel = Parcel.obtain();
    try (var file = context.openFileInput(DB_FILE)) {
      var bytes = new byte[1024];
      var read = file.read(bytes);
      parcel.unmarshall(bytes, 0, read);
      parcel.setDataPosition(0);
      return Todo.CREATOR.createFromParcel(parcel);
    } finally {
      parcel.recycle();
    }
  }

  @Override
  public void update(Todo item) throws IOException {
    var parcel = Parcel.obtain();
    try (var file = context.openFileOutput(DB_FILE, Context.MODE_PRIVATE)) {
      item.writeToParcel(parcel, 0);
      var bytes = parcel.marshall();
      file.write(bytes);
    } finally {
      parcel.recycle();
    }
  }
}
