package com.enixyu.camerashowcase;

import android.Manifest;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;

import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.List;

public class TakePhotoActivity extends AppCompatActivity {

  private final String TAG = getClass().getSimpleName();
  private final int REQUEST_CAMERA_PERMISSION = 1;

  private ImageView mTakePhotoButton;
  private ImageView mResultImage;
  private TextureView mPreview;

  private CameraDevice mCameraDevice;
  private CameraCaptureSession mCaptureSession;
  private CaptureRequest.Builder mPreviewRequestBuilder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_take_photo);

    mTakePhotoButton = findViewById(R.id.img_take_photo);
    mResultImage = findViewById(R.id.img_result);
    mPreview = findViewById(R.id.texture_preview);

    // 检查摄像头权限
    requestCameraPermission();
  }

  private void requestCameraPermission() {
    // 检查app是否已经拥有权限
    int grantResult = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
    boolean hasPermission = grantResult == PackageManager.PERMISSION_GRANTED;
    if (hasPermission) {
      // 已授权，可以直接打开摄像头
      initCamera();
      return;
    }

    // 未授权，请求app授权
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
        REQUEST_CAMERA_PERMISSION);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode != REQUEST_CAMERA_PERMISSION || grantResults.length == 0) {
      return;
    }

    boolean granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
    if (!granted) {
      Toast.makeText(this, "未授权使用摄像头", Toast.LENGTH_LONG).show();
      return;
    }

    // 授权成功，初始化摄像头
    initCamera();
  }

  @Override
  protected void onDestroy() {
    if (mCameraDevice != null) {
      mCameraDevice.close();
    }
    super.onDestroy();
  }

  private void initCamera() {
    Log.d(TAG, "初始化摄像头");
    mPreview.setSurfaceTextureListener(new SurfaceTextureListener() {
      @Override
      @RequiresPermission(permission.CAMERA)
      public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
        openCamera();
      }

      @Override
      public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        return true;
      }

      @Override
      public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i,
          int i1) {

      }

      @Override
      public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

      }
    });

    mTakePhotoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        captureStillImage();
      }
    });
  }

  @RequiresPermission(permission.CAMERA)
  private void openCamera() {
    CameraManager cameraManager = getSystemService(CameraManager.class);
    try {
      String cameraId = cameraManager.getCameraIdList()[0];
      cameraManager.openCamera(cameraId, new StateCallback() {
        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
          cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
          cameraDevice.close();
        }

        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
          mCameraDevice = cameraDevice;
          createCameraPreviewSession();
        }
      }, null);
    } catch (CameraAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private void createCameraPreviewSession() {
    try {
      SurfaceTexture texture = mPreview.getSurfaceTexture();
      texture.setDefaultBufferSize(mPreview.getWidth(), mPreview.getHeight());
      Surface surface = new Surface(texture);
      mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
      mPreviewRequestBuilder.addTarget(surface);
      mCameraDevice.createCaptureSession(List.of(surface), new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

        }

        @Override
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
          mCaptureSession = cameraCaptureSession;
          try {
            cameraCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(), null, null);
          } catch (CameraAccessException e) {
            Log.e(TAG, "创建预览会话失败", e);
          }
        }
      }, null);
    } catch (Exception e) {
      Log.e(TAG, "创建预览会话失败", e);
    }
  }

  private void captureStillImage() {
    if (mCameraDevice == null) {
      return;
    }

    Bitmap image = mPreview.getBitmap();
    mResultImage.setImageBitmap(image);
  }
}