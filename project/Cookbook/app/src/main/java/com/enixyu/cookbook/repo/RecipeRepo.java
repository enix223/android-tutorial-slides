package com.enixyu.cookbook.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.enixyu.cookbook.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepo {

  public final static RecipeRepo INSTANCE = new RecipeRepo();
  private CookbookSqliteHelper dbHelper;

  public void initialize(CookbookSqliteHelper dbHelper) {
    this.dbHelper = dbHelper;
  }

  /// 查询食谱
  public List<Recipe> selectAll() {
    try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
      List<Recipe> recipes = new ArrayList<>();
      String sql = "SELECT id, title, description, level, time_cost FROM recipe";
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
    try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
      Recipe recipe = null;
      String sql = "SELECT id, title, description, level, time_cost FROM recipe WHERE id = ?";
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
    try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
      ContentValues cv = new ContentValues();
      cv.put("title", recipe.title);
      cv.put("description", recipe.description);
      cv.put("level", recipe.level);
      cv.put("time_cost", recipe.timeCost);
      long rows = db.insert("recipe", null, cv);
      return rows > 0;
    }
  }

  /// 修改食谱
  public boolean update(Recipe recipe) {
    try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
      ContentValues cv = new ContentValues();
      cv.put("title", recipe.title);
      cv.put("description", recipe.description);
      cv.put("level", recipe.level);
      cv.put("time_cost", recipe.timeCost);
      long rows = db.update("recipe", cv, "id = ?", new String[]{String.valueOf(recipe.id)});
      return rows > 0;
    }
  }

  /// 删除食谱
  public boolean delete(Recipe recipe) {
    try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
      int rows = db.delete("recipe", "id = ?", new String[]{String.valueOf(recipe.id)});
      return rows > 0;
    }
  }
}
