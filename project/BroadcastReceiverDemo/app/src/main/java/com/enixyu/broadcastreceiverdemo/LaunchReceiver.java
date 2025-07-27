package com.enixyu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LaunchReceiver extends BroadcastReceiver {
  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
      Log.i(TAG, "接收到BOOT_COMPLETED广播");
    }
  }
}
