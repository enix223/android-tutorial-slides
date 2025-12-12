package com.enixyu.cookbook.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.cookbook.R;
import com.enixyu.cookbook.model.Recipe;
import com.enixyu.cookbook.repo.RecipeRepo;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private List<Recipe> mData;
  private RecipeListAdapter mAdapter;
  private TextView emptyView;
  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("爱厨房");

    listView = findViewById(R.id.list_view);
    emptyView = findViewById(R.id.tv_empty);

    mData = new ArrayList<>();
    mAdapter = new RecipeListAdapter(this, mData);
    listView.setAdapter(mAdapter);

    /// 点击跳转编辑
    listView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        gotoEdit(mData.get(i).id);
      }
    });

    /// 长按删除
    listView.setOnItemLongClickListener(new OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        delete(mData.get(i));
        return true;
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    reload();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_add) {
      // 跳转编辑页面
      gotoEdit(null);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /// 跳转编辑页面
  private void gotoEdit(@Nullable Integer id) {
    Intent intent = new Intent(this, EditActivity.class);
    if (id != null) {
      intent.putExtra("id", id);
    }
    startActivity(intent);
  }

  /// 删除食谱
  private void delete(Recipe recipe) {
    AlertDialog alertDialog = new AlertDialog.Builder(this)
        .setTitle("提示")
        .setMessage("是否确定删除该食谱")
        .setPositiveButton("确定", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            // 执行删除操作
            if (RecipeRepo.INSTANCE.delete(recipe)){
              Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_LONG).show();
              reload();
            } else {
              Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_LONG).show();
            }
          }
        })
        .create();
    alertDialog.show();
  }

  /// 刷新食谱
  private void reload() {
    mData.clear();
    List<Recipe> recipes = RecipeRepo.INSTANCE.selectAll();
    if (recipes.isEmpty()) {
      emptyView.setVisibility(View.VISIBLE);
      listView.setVisibility(View.GONE);
    } else {
      emptyView.setVisibility(View.GONE);
      listView.setVisibility(View.VISIBLE);
      mData.addAll(recipes);
      mAdapter.notifyDataSetChanged();
    }
  }
}