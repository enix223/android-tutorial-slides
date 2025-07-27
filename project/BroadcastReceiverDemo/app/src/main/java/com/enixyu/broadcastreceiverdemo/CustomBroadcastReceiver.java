package com.enixyu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "接收到自定义标准通知");
    Toast.makeText(context, "接收到自定义标准广播", Toast.LENGTH_LONG).show();
  }
}
