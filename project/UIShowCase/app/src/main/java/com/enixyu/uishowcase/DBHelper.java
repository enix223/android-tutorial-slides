package com.enixyu.uishowcase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  public DBHelper(Context context) {
    super(context, "student.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(
        "CREATE TABLE student ("
            + "id INTEGER PRIMARY KEY, "
            + "name TEXT, "
            + "gender TEXT, "
            + "age INTEGER, "
            + "hobbies TEXT"
            + ")"
    );
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  }
}
