package com.enixyu.bleshowcase;

import android.annotation.SuppressLint;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BleScanActivity extends AppCompatActivity implements OnScanResult {

  private final int PERMISSION_REQUEST_CODE = 99;
  // 是否允许使用蓝牙
  private boolean mBlePermit;
  private ScanResultAdapter mScanResultAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ble_scan);
    ListView mListView = findViewById(R.id.list_view);
    mBlePermit = PermissionHelper.checkPermission(this, PERMISSION_REQUEST_CODE);
    mScanResultAdapter = new ScanResultAdapter(this, R.layout.item_scan_result);
    mListView.setAdapter(mScanResultAdapter);
  }

  /// 检查授权结果
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode != PERMISSION_REQUEST_CODE) {
      return;
    }
    mBlePermit = PermissionHelper.onRequestPermissionsResult(requestCode, permissions,
        grantResults);
    if (!mBlePermit) {
      AlertHelper.showMessage(this, "APP未授权使用蓝牙");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.scan_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.scan) {
      onScanClick(item);
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressLint("MissingPermission")
  private void onScanClick(MenuItem item) {
    if (!mBlePermit) {
      AlertHelper.showMessage(this, "APP未授权使用蓝牙");
      return;
    }
    BluetoothHelper bleHelper = getBleHelper();
    if (bleHelper.isScanning()) {
      bleHelper.stopScan();
      item.setTitle("扫描");
    } else {
      bleHelper.startScan(this);
      item.setTitle("停止");
    }
  }

  private BluetoothHelper getBleHelper() {
    return BluetoothHelper.getInstance(getApplication());
  }

  @Override
  public void onScanResult(int errorCode, @Nullable ScanResult result) {
    if (errorCode != 0) {
      AlertHelper.showMessage(this, "扫描失败");
      return;
    }

    mScanResultAdapter.addItem(result);
  }
}
