package com.enixyu.cookbook.ui;

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
import com.enixyu.cookbook.R;
import com.enixyu.cookbook.model.Recipe;
import com.enixyu.cookbook.repo.RecipeRepo;

public class EditActivity extends AppCompatActivity {

  private EditText titleEditText;
  private RatingBar ratingBar;
  private SeekBar timeCostSeekBar;
  private EditText descEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);
    setTitle("编辑食谱");

    // 显示返回按钮
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setDisplayHomeAsUpEnabled(true);

    titleEditText = findViewById(R.id.edit_title);
    ratingBar = findViewById(R.id.rating_level);
    timeCostSeekBar = findViewById(R.id.seekbar_time_cost);
    descEditText = findViewById(R.id.edit_description);

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

  private void load() {
    int id = getIntent().getIntExtra("id", 0);
    Recipe recipe = RecipeRepo.INSTANCE.select(id);
    if (recipe == null) {
      return;
    }
    titleEditText.setText(recipe.title);
    descEditText.setText(recipe.description);
    ratingBar.setRating(recipe.level);
    timeCostSeekBar.setProgress(recipe.timeCost);
  }

  private void save() {
    int id = getIntent().getIntExtra("id", 0);
    if (id == 0) {
      // 添加新食谱
      Recipe recipe = new Recipe(0, titleEditText.getText().toString(),
          descEditText.getText().toString(), ratingBar.getRating(), timeCostSeekBar.getProgress());
      if (RecipeRepo.INSTANCE.insert(recipe)) {
        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "添加失败", Toast.LENGTH_LONG).show();
      }
    } else {
      // 修改现有食谱
      Recipe recipe = RecipeRepo.INSTANCE.select(id);
      if (recipe == null) {
        return;
      }
      recipe.title = titleEditText.getText().toString();
      recipe.description = descEditText.getText().toString();
      recipe.level = ratingBar.getRating();
      recipe.timeCost = timeCostSeekBar.getProgress();
      if (RecipeRepo.INSTANCE.update(recipe)) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "修改失败", Toast.LENGTH_LONG).show();
      }
    }
  }
}
