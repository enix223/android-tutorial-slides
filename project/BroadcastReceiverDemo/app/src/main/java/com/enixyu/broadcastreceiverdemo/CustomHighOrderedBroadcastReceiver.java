package com.enixyu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomHighOrderedBroadcastReceiver extends BroadcastReceiver {

  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "接收到自定义有序通知");
    Toast.makeText(context, "高优先级: 接收到自定义有序广播", Toast.LENGTH_LONG).show();
    if (intent.getBooleanExtra("interrupt", false)) {
      abortBroadcast();
    }
  }
}
