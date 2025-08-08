package com.enixyu.fileoperationdemo.db;

import android.content.Context;
import android.os.Parcel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/// 文件格式
///
/// | B0 ~ B3 | B4 ~ B7 | B8 ~ B11 | B12 ~ B15 | B16 ~ B19 |
/// |-|-|-|-|-|
/// | 魔数 (0x22 0x33 0x44 0x55) | 记录总数 | 记录1长度 | 记录1数据 | 记录2长度 | 记录2数据 |
public class FileDataProvider implements DataProvider {

  private final String DB_FILE = "db.fd";
  private final Context context;
  private final byte[] MAGIC_NUMBER = new byte[]{0x22, 0x33, 0x44, 0x55};

  public FileDataProvider(Context context) {
    this.context = context;
  }

  @Override
  public List<Todo> read() throws IOException {
    try (var file = context.openFileInput(DB_FILE)) {
      // 读取魔数
      var buffer = new byte[4];
      var read = file.read(buffer, 0, 4);
      if (!Arrays.equals(buffer, MAGIC_NUMBER) || read != 4) {
        throw new IOException("文件格式不合法");
      }

      // 读取记录总数
      read = file.read(buffer, 4, 4);
      if (read != 4) {
        throw new IOException("文件格式不合法");
      }
      var totalRecords = getInt32(buffer);
      if (totalRecords < 0) {
        throw new IOException("文件记录数不合法");
      }

      // 顺序读取记录
      var list = new ArrayList<Todo>(totalRecords);
      var offset = 8;
      for (var i = 0; i < totalRecords; i ++) {
        read = file.read(buffer, offset, 4);
        var objLen = getInt32(buffer);
        var objBytes = new byte[objLen];
        var parcel = Parcel.obtain();
        parcel.unmarshall(objBytes, 0, read);
        parcel.setDataPosition(0);
        var todo = Todo.CREATOR.createFromParcel(parcel);
        parcel.recycle();
      }
      return list;
    }
  }

  @Override
  public void create(Todo item) throws IOException {

  }

  @Override
  public void update(Todo item) throws IOException {
    try (var file = context.openFileOutput(DB_FILE, Context.MODE_PRIVATE)) {
      var parcel = Parcel.obtain();
      item.writeToParcel(parcel, 0);
      var bytes = parcel.marshall();
      file.write(bytes);
      parcel.recycle();
    }
  }

  private int getInt32(byte[] buffer) {
    return (buffer[0] << 24) | (buffer[1] << 16) | (buffer[2] << 8) | buffer[3];
  }
}
