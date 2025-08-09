package com.enixyu.fileoperationdemo.db;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.alibaba.fastjson2.JSON;
import java.io.IOException;

public class SharedPrefDataProvider implements DataProvider {

  private final Context context;

  public SharedPrefDataProvider(Context context) {
    this.context = context;
  }

  @Nullable
  @Override
  public Todo read() throws IOException {
    var pref = getSharedPreferences();
    var json = pref.getString("todo", null);
    return JSON.parseObject(json, Todo.class);
  }

  @Override
  public void update(Todo item) throws IOException {
    var json = JSON.toJSONString(item);
    var pref = getSharedPreferences();
    var edit = pref.edit();
    edit.putString("todo", json);
    edit.apply();
  }

  private SharedPreferences getSharedPreferences() {
    return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
  }
}
