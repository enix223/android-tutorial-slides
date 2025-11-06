package com.enixyu.bleshowcase;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

  public static boolean checkPermission(Activity context, int requestCode) {
    List<String> requiredPermissions = new ArrayList<>();
    if (VERSION.SDK_INT >= VERSION_CODES.S) {
      // 请求扫描权限
      var res = ContextCompat.checkSelfPermission(context, permission.BLUETOOTH_SCAN);
      if (res == PackageManager.PERMISSION_DENIED) {
        requiredPermissions.add(permission.BLUETOOTH_SCAN);
      }
      // 请求连接权限
      res = ContextCompat.checkSelfPermission(context, permission.BLUETOOTH_CONNECT);
      if (res == PackageManager.PERMISSION_DENIED) {
        requiredPermissions.add(permission.BLUETOOTH_CONNECT);
      }
    } else {
      // 请求定位权限
      var res = ContextCompat.checkSelfPermission(context, permission.ACCESS_FINE_LOCATION);
      if (res == PackageManager.PERMISSION_DENIED) {
        requiredPermissions.add(permission.ACCESS_FINE_LOCATION);
      }
    }
    if (requiredPermissions.isEmpty()) {
      return true;
    }

    ActivityCompat.requestPermissions(context, requiredPermissions.toArray(new String[0]), requestCode);
    return false;
  }

  public static boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    String missingPermission = null;
    for (var i = 0; i < permissions.length; i ++) {
      if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
        missingPermission = permissions[i];
        break;
      }
    }

    return missingPermission == null;
  }
}
