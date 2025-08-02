package com.enixyu.fileoperationdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SqliteDataProvider extends SQLiteOpenHelper implements DataProvider {

  private static final String TAG = "SqliteDataProvider";
  private static final String DB_NAME = "sqlite.db";
  private static final int DB_VERSION = 20250801;
  private final Context context;

  public SqliteDataProvider(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    this.context = context;
  }

  @Override
  public List<Todo> read() {
    return List.of();
  }

  @Override
  public void write(Todo item) {

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
