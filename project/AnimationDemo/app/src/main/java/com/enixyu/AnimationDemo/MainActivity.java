package com.enixyu.AnimationDemo;

import android.content.Intent;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private Button btnFrame, btnView, btnProerty;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 初始化UI组件
    initViews();

    // 设置按钮点击事件
    setupButtonListeners();
  }

  /**
   * 初始化所有View组件
   */
  private void initViews() {

    // 找到所有按钮
    btnFrame = findViewById(R.id.btn_frame);
    btnView = findViewById(R.id.btn_view);
    btnProerty = findViewById(R.id.btn_property);
  }

  /**
   * 设置按钮点击事件监听器
   */
  private void setupButtonListeners() {
    // 开始按钮 - 点击开始动画
    btnFrame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, FrameAnimationActivity.class);
        startActivity(intent);
      }
    });

    // 停止按钮 - 点击停止动画
    btnView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ViewAnimationActivity.class);
        startActivity(intent);
      }
    });
    btnProerty.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
        startActivity(intent);
      }
    });
  }
}