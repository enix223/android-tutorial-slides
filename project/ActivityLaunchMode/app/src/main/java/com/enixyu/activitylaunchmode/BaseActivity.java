package com.enixyu.activitylaunchmode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

  private static final String TAG = "ActivityLaunchMode";

  protected void log(String message) {
    Log.i(TAG, this + ": " + message);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("onCreate");
    setContentView(R.layout.activity_launch_mode);

    Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.rgb(0, 0, 0));

    // 配置导航栏
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && !isTaskRoot()) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // 注册按钮的点击事件
    Button btn1 = findViewById(R.id.btn1);
    btn1.setOnClickListener(this);

    Button btn2 = findViewById(R.id.btn2);
    btn2.setOnClickListener(this);

    Button btn3 = findViewById(R.id.btn3);
    btn3.setOnClickListener(this);

    Button btn4 = findViewById(R.id.btn4);
    btn4.setOnClickListener(this);

    Button btn5 = findViewById(R.id.btn5);
    btn5.setOnClickListener(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    log("onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    log("onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    log("onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    log("onStop");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    log("onRestart");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    log("onDestroy");
  }

  /**
   * 处理点击事件
   *
   * @param view 触发点击事件的视图
   */
  @Override
  public void onClick(View view) {
    Class<?> targetCls = null;
    if (view.getId() == R.id.btn1) {
      targetCls = StandardActivity.class;
    } else if (view.getId() == R.id.btn2) {
      targetCls = SingleTopActivity.class;
    } else if (view.getId() == R.id.btn3) {
      targetCls = SingleTaskActivity.class;
    } else if (view.getId() == R.id.btn4) {
      targetCls = SingleInstanceActivity.class;
    } else if (view.getId() == R.id.btn5) {
      targetCls = SingleInstancePerTaskActivity.class;
    }
    Intent intent = new Intent(this, targetCls);
    startActivity(intent);
  }

  /**
   * 响应导航栏菜单按钮点击事件
   *
   * @param item 导航栏菜单
   * @return 是否以处理
   */
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
