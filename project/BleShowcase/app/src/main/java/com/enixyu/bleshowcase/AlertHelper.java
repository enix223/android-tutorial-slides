package com.enixyu.bleshowcase;

import android.content.Context;
import android.widget.Toast;

public class AlertHelper {

  /// 消息提示
  public static void showMessage(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
