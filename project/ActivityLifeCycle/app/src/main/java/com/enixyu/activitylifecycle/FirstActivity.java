package com.enixyu.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

  private static final String TAG = "ActivityLifeCycle";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("onCreate");

    // 设置layout
    setContentView(R.layout.activity_first);

    // 注册按钮点击事件
    Button btn = findViewById(R.id.btn_jump);
    btn.setOnClickListener(view -> {
      // 跳转第二页
      Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
      startActivity(intent);
    });
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
  protected void onDestroy() {
    log("onDestroy");
    super.onDestroy();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    log("onRestart");
  }

  private void log(String message) {
    Log.i(TAG, this + ":" + message);
  }
}
