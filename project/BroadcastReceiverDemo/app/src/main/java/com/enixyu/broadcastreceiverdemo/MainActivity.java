package com.enixyu.broadcastreceiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  // 创建动态注册广播接收者
  private final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // ----------- 动态注册 ------------
    IntentFilter intentFilter = new IntentFilter();
    // 动态注册时间变化
    intentFilter.addAction("android.intent.action.TIME_TICK");
    // 动态注册屏幕亮起广播
    intentFilter.addAction("android.intent.action.SCREEN_ON");
    // 动态注册屏幕关闭广播
    intentFilter.addAction("android.intent.action.SCREEN_OFF");
    // 注册广播接收者
    registerReceiver(myBroadcastReceiver, intentFilter);

    Button standardBroadcastBtn = findViewById(R.id.btn_send_standard_broadcast);
    standardBroadcastBtn.setOnClickListener((v) -> {
      Intent intent = new Intent("com.enixyu.broadcastreceiverdemo.CUSTOM_STANDARD_BROADCAST");
      intent.setPackage(getPackageName());
      // 发送标准广播
      sendBroadcast(intent);
    });

    Button orderedBroadcastBtn = findViewById(R.id.btn_send_ordered_broadcast);
    orderedBroadcastBtn.setOnClickListener((v) -> {
      Intent intent = new Intent("com.enixyu.broadcastreceiverdemo.CUSTOM_ORDERED_BROADCAST");
      intent.setPackage(getPackageName());
      intent.putExtra("interrupt", false);
      // 发送有序广播
      sendOrderedBroadcast(intent, null);
    });

    Button interruptedBroadcastBtn = findViewById(R.id.btn_send_interrupted_broadcast);
    interruptedBroadcastBtn.setOnClickListener((v) -> {
      Intent intent = new Intent("com.enixyu.broadcastreceiverdemo.CUSTOM_ORDERED_BROADCAST");
      intent.setPackage(getPackageName());
      intent.putExtra("interrupt", true);
      // 发送可中断的有序广播
      sendOrderedBroadcast(intent, null);
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 移除广播接收者，回收资源
    unregisterReceiver(myBroadcastReceiver);
  }
}
