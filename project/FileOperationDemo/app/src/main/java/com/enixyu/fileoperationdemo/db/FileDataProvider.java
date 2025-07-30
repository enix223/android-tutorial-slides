package com.enixyu.fileoperationdemo.db;

import android.content.Context;
import android.os.Parcel;
import java.io.IOException;
import java.util.List;

public class FileDataProvider implements DataProvider {

  private final String DB_FILE = "file-db.json";
  private final Context context;

  public FileDataProvider(Context context) {
    this.context = context;
  }

  @Override
  public List<Todo> read()  throws IOException{
    try (var file = context.openFileInput(DB_FILE)) {
      byte[] bytes = new byte[file.available()];
      var read = file.read(bytes);

      var parcel = Parcel.obtain();
      parcel.unmarshall(bytes, 0, read);
      parcel.setDataPosition(0);
      var todo = Todo.CREATOR.createFromParcel(parcel);
      parcel.recycle();
      return List.of(todo);
    }
  }

  @Override
  public void write(Todo item) throws IOException {
    try (var file = context.openFileOutput(DB_FILE, Context.MODE_PRIVATE)) {
      var parcel = Parcel.obtain();
      item.writeToParcel(parcel, 0);
      var bytes = parcel.marshall();
      file.write(bytes);
      parcel.recycle();
    }
  }
}
