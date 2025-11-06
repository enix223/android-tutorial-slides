package com.enixyu.bleshowcase;

import android.bluetooth.le.ScanResult;
import androidx.annotation.Nullable;

public interface OnScanResult {

  void onScanResult(int errorCode, @Nullable ScanResult result);
}
