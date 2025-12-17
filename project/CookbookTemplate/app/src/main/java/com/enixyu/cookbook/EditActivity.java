package com.enixyu.cookbook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

  private EditText titleEditText;
  private RatingBar ratingBar;
  private SeekBar timeCostSeekBar;
  private EditText descEditText;
  private RecipeRepo recipeRepo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);
    setTitle("编辑食谱");

    recipeRepo = new RecipeRepo(this);

    // 显示返回按钮
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setDisplayHomeAsUpEnabled(true);

    // TODO: 添加组件引用 (参考学生信息表单练习代码)

    load();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_edit, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_save) {
      // 保存
      save();
    } else if (item.getItemId() == android.R.id.home) {
      // 返回上一页
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  /// 从数据库加在数据
  private void load() {
    int id = getIntent().getIntExtra("id", 0);
    Recipe recipe = recipeRepo.select(id);
    if (recipe == null) {
      return;
    }
    // TODO: 绑定数据到控件上 (参考学生信息表单练习代码)
  }

  private void save() {
    int id = getIntent().getIntExtra("id", 0);
    if (id == 0) {
      // TODO: 添加新食谱
    } else {
      // 修改现有食谱
      Recipe recipe = recipeRepo.select(id);
      if (recipe == null) {
        return;
      }
      // TODO: 修改食谱
    }
  }
}
