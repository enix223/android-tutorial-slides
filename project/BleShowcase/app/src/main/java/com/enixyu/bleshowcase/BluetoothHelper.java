package com.enixyu.bleshowcase;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class BluetoothHelper {

  private final String TAG = "BluetoothHelper";
  private static BluetoothHelper mInstance;
  private final Application mContext;
  @Nullable
  private BluetoothManager mBleManager;
  @Nullable
  private BluetoothAdapter mBleAdapter;
  @Nullable
  private BluetoothLeScanner mBleScanner;
  private final AtomicBoolean mScanning = new AtomicBoolean(false);
  private final AtomicReference<String> mConnectingDeviceAddress = new AtomicReference<>(null);
  private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
    @Override
    public void onCharacteristicChanged(@NonNull BluetoothGatt gatt,
        @NonNull BluetoothGattCharacteristic characteristic, @NonNull byte[] value) {
      super.onCharacteristicChanged(gatt, characteristic, value);
    }

    @Override
    @RequiresPermission(permission.BLUETOOTH_CONNECT)
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
      super.onConnectionStateChange(gatt, status, newState);
      BluetoothDevice device = gatt.getDevice();
      Log.d(TAG,
          String.format("设备连接状态改变, device = %s, state = %d", device.getName(), newState));
      if (device.getAddress().equals(mConnectingDeviceAddress.get())) {
        gatt.discoverServices();
      }
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
      super.onServicesDiscovered(gatt, status);
    }
  };

  public static BluetoothHelper getInstance(Application application) {
    if (mInstance != null) {
      return mInstance;
    }
    synchronized (BluetoothHelper.class) {
      if (mInstance == null) {
        mInstance = new BluetoothHelper(application);
      }
      return mInstance;
    }
  }

  public BluetoothHelper(Application mContext) {
    this.mContext = mContext;
  }

  @Nullable
  private BluetoothManager getBleManager() {
    if (mBleManager == null) {
      Object value = mContext.getSystemService(Context.BLUETOOTH_SERVICE);
      if (value == null) {
        return null;
      }
      mBleManager = (BluetoothManager) value;
    }
    return mBleManager;
  }

  @Nullable
  private BluetoothAdapter getBleAdapter() {
    if (mBleAdapter != null) {
      return mBleAdapter;
    }
    BluetoothManager manager = getBleManager();
    if (manager == null) {
      return null;
    }
    mBleAdapter = manager.getAdapter();
    return mBleAdapter;
  }

  @Nullable
  private BluetoothLeScanner getScanner() {
    if (mBleScanner != null) {
      return mBleScanner;
    }
    BluetoothAdapter adapter = getBleAdapter();
    if (adapter == null) {
      return null;
    }
    mBleScanner = adapter.getBluetoothLeScanner();
    return mBleScanner;
  }

  /// 蓝牙是否可用
  public boolean isBluetoothEnable() {
    BluetoothManager manager = getBleManager();
    if (manager == null) {
      return false;
    }
    BluetoothAdapter adapter = manager.getAdapter();
    return adapter != null && adapter.isEnabled();
  }

  /// 开始扫描
  @RequiresPermission(permission.BLUETOOTH_SCAN)
  public boolean startScan(OnScanResult scanCallback) {
    BluetoothLeScanner scanner = getScanner();
    if (scanner == null) {
      return false;
    }
    ScanSettings settings = new Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build();
    scanner.startScan(null, settings, new ScanCallback() {
      @Override
      @SuppressLint("MissingPermission")
      public void onScanResult(int callbackType, ScanResult result) {
        super.onScanResult(callbackType, result);
        scanCallback.onScanResult(0, result);
        Log.d(TAG, "找到蓝牙外设: " + result.getDevice().getName());
      }

      @Override
      @SuppressLint("MissingPermission")
      public void onBatchScanResults(List<ScanResult> results) {
        super.onBatchScanResults(results);
        for (ScanResult result : results) {
          Log.d(TAG, "找到蓝牙外设: " + result.getDevice().getName());
          scanCallback.onScanResult(0, result);
        }
      }

      @Override
      public void onScanFailed(int errorCode) {
        super.onScanFailed(errorCode);
        scanCallback.onScanResult(errorCode, null);
        mScanning.set(false);
        Log.d(TAG, "扫描蓝牙外设失败: " + errorCode);
      }
    });
    mScanning.set(true);
    Log.d(TAG, "开始扫描蓝牙外设");
    return true;
  }

  /// 停止扫描
  @RequiresPermission(permission.BLUETOOTH_SCAN)
  public void stopScan() {
    BluetoothLeScanner scanner = getScanner();
    if (scanner == null) {
      return;
    }
    scanner.stopScan(new ScanCallback() {
    });
    mScanning.set(false);
    Log.d(TAG, "停止扫描蓝牙外设");
  }

  public boolean isScanning() {
    return mScanning.get();
  }

  @RequiresPermission(permission.BLUETOOTH_CONNECT)
  public void connect(ScanResult scanResult) {
    BluetoothDevice device = scanResult.getDevice();
    if (device == null) {
      return;
    }
    Log.d(TAG, "开始连接蓝牙外设: " + device.getName());
    mConnectingDeviceAddress.set(device.getAddress());
    device.connectGatt(mContext, true, mGattCallback);
  }
}
