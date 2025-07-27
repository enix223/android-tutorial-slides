package com.enixyu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicBroadcastReceiver extends BroadcastReceiver {

  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "时间变化: " + intent);
  }
}
