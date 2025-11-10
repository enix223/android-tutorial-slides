package com.enixyu.mqttcontroller.ui;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.StringRes;

public class AlertHelper {

  public static void showToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  public static void showToast(Context context, @StringRes int messageId) {
    Toast.makeText(context, messageId, Toast.LENGTH_LONG).show();
  }
}
