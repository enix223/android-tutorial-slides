package com.enixyu.cookbook.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CookbookSqliteHelper extends SQLiteOpenHelper {

  public CookbookSqliteHelper(Context context) {
    super(context, "cookbook.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE recipe ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "title TEXT, "
        + "description TEXT, "
        + "level INTEGER, "
        + "time_cost INTEGER, "
        + "category TEXT"
        + ")");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  }
}
