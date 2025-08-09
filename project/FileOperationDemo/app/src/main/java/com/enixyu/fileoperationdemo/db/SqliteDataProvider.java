package com.enixyu.fileoperationdemo.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SqliteDataProvider extends SQLiteOpenHelper implements DataProvider {

  private static final String TAG = "SqliteDataProvider";
  private static final String DB_NAME = "sqlite.db";
  private static final int DB_VERSION = 20250801;
  private final Context context;

  public SqliteDataProvider(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    this.context = context;
  }

  @Nullable
  @Override
  @SuppressLint("Range")
  public Todo read() {
    try (var db = getReadableDatabase(); var cursor = db.query("todo", null, null, null, null, null,
        null)) {
      if (!cursor.moveToFirst()) {
        return null;
      }
      var title = cursor.getString(cursor.getColumnIndex("title"));
      var done = cursor.getInt(cursor.getColumnIndex("done")) == 1;
      return new Todo(title, done);
    }
  }

  @Override
  public void update(Todo item) {
    ;
    var values = new ContentValues();
    values.put("title", item.getTitle());
    values.put("done", item.isDone());
    try (var db = getWritableDatabase(); var cursor = db.query("todo", null, null, null, null, null,
        null)) {
      if (cursor.getCount() == 0) {
        // 创建记录
        var inserted = db.insert("todo", null, values);
        Log.d(TAG, String.format("插入记录数量: %d", inserted));
      } else {
        // 修改记录
        var updated = db.update("todo", values, null, null);
        Log.d(TAG, String.format("更新记录数量: %d", updated));
      }
    }
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    try {
      applyMigrations(sqLiteDatabase, DB_VERSION);
    } catch (IOException e) {
      Log.e(TAG, "初始化数据库失败");
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    try {
      applyMigrations(sqLiteDatabase, newVersion);
    } catch (IOException e) {
      Log.e(TAG, "执行数据库迁移失败");
      throw new RuntimeException(e);
    }
  }

  private String loadSQL(InputStream is) throws IOException {
    var reader = new BufferedReader(new InputStreamReader(is));
    var stringBuffer = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuffer.append(line).append("\n");
    }
    return stringBuffer.toString();
  }

  private void applyMigrations(SQLiteDatabase db, int version) throws IOException {
    var assets = context.getAssets();
    var dir = "migrations" + "/" + version;
    var migrations = assets.list(dir);
    if (migrations == null) {
      return;
    }
    // 按文件名排序
    Arrays.sort(migrations);
    for (var filename : migrations) {
      try (var is = assets.open(dir + "/" + filename)) {
        // 执行迁移语句
        var sql = loadSQL(is);
        db.execSQL(sql);

        // 写入迁移历史
        var cv = new ContentValues();
        cv.put("name", filename);
        cv.put("applied_at", System.currentTimeMillis());
        db.insert("migrations", null, cv);
      }
    }
  }
}
