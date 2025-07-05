package com.enixyu.activitylaunchmode;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

  private static final String TAG = "ActivityLaunchMode";

  protected void log(String message) {
    Log.i(TAG, this + ": " + message);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("onCreate");
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
  protected void onRestart() {
    super.onRestart();
    log("onRestart");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    log("onDestroy");
  }
}
