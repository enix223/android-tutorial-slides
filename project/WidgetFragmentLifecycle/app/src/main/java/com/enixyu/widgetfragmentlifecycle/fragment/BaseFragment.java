package com.enixyu.widgetfragmentlifecycle.fragment;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

  private final String TAG = this.getClass().getName();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.i(TAG, "onCreate: " + this);
  }

  @Override
  public void onStart() {
    super.onStart();
    Log.i(TAG, "onStart: " + this);
  }

  @Override
  public void onResume() {
    super.onResume();
    Log.i(TAG, "onResume: " + this);
  }

  @Override
  public void onPause() {
    super.onPause();
    Log.i(TAG, "onPause: " + this);
  }

  @Override
  public void onStop() {
    super.onStop();
    Log.i(TAG, "onStop: " + this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "onDestroy: " + this);
  }
}
