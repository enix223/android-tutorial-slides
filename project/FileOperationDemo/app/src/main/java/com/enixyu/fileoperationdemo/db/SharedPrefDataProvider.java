package com.enixyu.fileoperationdemo.db;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
import java.util.List;
import com.alibaba.fastjson2.JSON;

public class SharedPrefDataProvider implements DataProvider {

  private final Context context;

  public SharedPrefDataProvider(Context context) {
    this.context = context;
  }

  @Override
  public List<Todo> read() throws IOException {
    var pref = getSharedPreferences();
    var json = pref.getString("todo", null);
    var todo = JSON.parseObject(json, Todo.class);
    if (todo == null) {
      throw new RuntimeException("解析json失败");
    }
    return List.of(todo);
  }

  @Override
  public void create(Todo item) throws IOException {

  }

  @Override
  public void update(Todo item) throws IOException {
    var json = JSON.toJSONString(item);
    var pref = getSharedPreferences();
    var edit = pref.edit();
    edit.putString("todo", json);
    edit.apply();
  }

  private SharedPreferences getSharedPreferences() throws IOException {
    return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
  }
}
