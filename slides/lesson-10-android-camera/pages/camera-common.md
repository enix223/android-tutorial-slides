- 权限与清单配置

```xml
<manifest ...>

<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-feature android:name="android.hardware.camera" android:required="true"/>

</manifest>
```

- Android 6.0（API 23）起，权限需在运行时动态请求。

---

- activity_main


```xml 
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent">
  
  <TextureView
    android:id="@+id/textureView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    />
    
  <ImageView
    android:id="@+id/imageView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:layout_below="@id/textureView"
    android:layout_centerInParent="true"
    android:scaleType="fitCenter"/>
    
  <Button
    android:id="@+id/takePictureButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="拍照"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true"
    android:layout_marginBottom="20dp"/>
</RelativeLayout>
```

---


```java
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.*;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_CAMERA_PERMISSION = 200; // 动态权限请求码

  private TextureView textureView;  // 用于显示相机预览
  private ImageView imageView;      // 用于显示拍照后的图片
  private Button takePictureButton;

  private CameraDevice cameraDevice;             // 摄像头设备对象
  private CameraCaptureSession captureSession;  // 摄像头捕获会话
```

---

```java

  private CaptureRequest.Builder previewRequestBuilder; // 预览请求构建器

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textureView = findViewById(R.id.textureView);
    imageView = findViewById(R.id.imageView);
    takePictureButton = findViewById(R.id.takePictureButton);

    // 权限检查
    if (checkCameraPermission()) {
      startPreview();  // 有权限直接开启预览
    } else {
      requestCameraPermission(); // 否则请求权限
    }

    takePictureButton.setOnClickListener(v -> takePicture()); // 拍照按钮点击事件
  }

  private boolean checkCameraPermission() {   // 检查是否已经获取相机权限
    return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED;
  }

```

---

```java

  private void requestCameraPermission() { // 动态请求相机权限
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {  // 权限请求回调
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CAMERA_PERMISSION) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        startPreview(); // 权限授予，开启预览
      } else {
        finish();  // 权限拒绝，关闭应用
      }
    }
  }

  private void startPreview() {
    textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {   // 给 TextureView 设置监听器
      @Override
      public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {  // 当 TextureView 准备好时调用
        openCamera();
      }
      @Override public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {}
      @Override public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) { return true; }
      @Override public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {}
    });
  }
```

---

```java

  private void openCamera() { // 打开摄像头
    CameraManager manager = (CameraManager) getSystemService(CAMERA_SERVICE);
    try {
      String cameraId = manager.getCameraIdList()[0]; // 默认选择后置摄像头
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) return;

      manager.openCamera(cameraId, new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
          cameraDevice = camera; // 保存摄像头对象
          createCameraPreviewSession();  // 创建预览会话
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) { camera.close(); }
        @Override
        public void onError(@NonNull CameraDevice camera, int error) { camera.close(); }
      }, null);
    } catch (Exception e) { e.printStackTrace(); }
  }

  private void createCameraPreviewSession() {
    try {
      SurfaceTexture texture = textureView.getSurfaceTexture();
      texture.setDefaultBufferSize(640, 480);
      Surface surface = new Surface(texture); // 创建 Surface

```

---

```java

      previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);   // 创建预览请求
      previewRequestBuilder.addTarget(surface); // 设置输出目标为 TextureView

      cameraDevice.createCaptureSession(Collections.singletonList(surface),
          new CameraCaptureSession.StateCallback() { // 创建捕获会话
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {
              captureSession = session;
              try {
                captureSession.setRepeatingRequest(previewRequestBuilder.build(), null, null);  // 设置重复请求，实现连续预览
              } catch (CameraAccessException e) { e.printStackTrace(); }
            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {}
          }, null);
    } catch (CameraAccessException e) { e.printStackTrace(); }
  }

  private void takePicture() {
    if (cameraDevice == null) return;

    // 获取当前 TextureView 画面
    Bitmap bitmap = textureView.getBitmap();
    imageView.setImageBitmap(bitmap);
  }

```

---

```java

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (cameraDevice != null) cameraDevice.close();
  }
}
```