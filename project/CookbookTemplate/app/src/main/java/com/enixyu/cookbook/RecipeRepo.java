package com.enixyu.cookbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/// 数据库操作类
public class RecipeRepo extends SQLiteOpenHelper {

  public RecipeRepo(Context context) {
    super(context, "cookbook.db", null, 1);
  }

  // TODO: 使用sql语句创建recipe表 （参考数据库ppt的创建表create table）
  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  }

  /// 查询食谱
  public List<Recipe> selectAll() {
    try (SQLiteDatabase db = getReadableDatabase()) {
      List<Recipe> recipes = new ArrayList<>();
      // TODO: 替换查询全表的sql语句
      String sql = "";
      try (Cursor cursor = db.rawQuery(sql, null)) {
        while (cursor.moveToNext()) {
          Recipe recipe = new Recipe(
              cursor.getInt(0),
              cursor.getString(1),
              cursor.getString(2),
              cursor.getInt(3),
              cursor.getInt(4)
          );
          recipes.add(recipe);
        }
      }
      return recipes;
    }
  }

  /// 根据ID查询指定食谱
  public Recipe select(int id) {
    try (SQLiteDatabase db = getReadableDatabase()) {
      Recipe recipe = null;
      // TODO: 替换查询具体一行的sql语句 (id = ?)
      String sql = "";
      try (Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)})) {
        while (cursor.moveToNext()) {
          recipe = new Recipe(
              cursor.getInt(0),
              cursor.getString(1),
              cursor.getString(2),
              cursor.getInt(3),
              cursor.getInt(4)
          );
        }
      }
      return recipe;
    }
  }

  /// 添加食谱
  public boolean insert(Recipe recipe) {
    try (SQLiteDatabase db = getWritableDatabase()) {
      ContentValues cv = new ContentValues();
      // TODO: 添加构造映射表 (参考数据库ppt的插入数据INSERT章节)
      long rows = db.insert("recipe", null, cv);
      return rows > 0;
    }
  }

  /// 修改食谱
  public boolean update(Recipe recipe) {
    try (SQLiteDatabase db = getWritableDatabase()) {
      ContentValues cv = new ContentValues();
      // TODO: 添加构造映射表 (参考数据库ppt的修改数据UPDATE章节)
      long rows = db.update("recipe", cv, "id = ?", new String[]{String.valueOf(recipe.id)});
      return rows > 0;
    }
  }

  /// 删除食谱
  public boolean delete(Recipe recipe) {
    try (SQLiteDatabase db = getWritableDatabase()) {
      // TODO: 添加调用delete函数 (参考数据库ppt的删除记录DELETE章节)
      int rows =
      return rows > 0;
    }
  }
}
