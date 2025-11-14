package com.enixyu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
      Log.i(TAG, "APP启动完成");
    } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
      Log.i(TAG, "屏幕亮起");
      Toast.makeText(context, "屏幕亮起", Toast.LENGTH_SHORT).show();
    } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
      Log.i(TAG, "屏幕关闭");
      Toast.makeText(context, "屏幕关闭", Toast.LENGTH_SHORT).show();
    } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
      Log.d(TAG, "时间变化");
    }
  }
}
