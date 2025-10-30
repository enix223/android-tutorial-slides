# 权限与清单配置

```xml
<manifest ...>

<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-feature android:name="android.hardware.camera" android:required="true"/>

</manifest>
```

---

# 动态申请权限
Android 6.0（API 23）起，权限需在运行时动态请求。

1. 定义请求代码

```java
private final int REQUEST_CAMERA_PERMISSION = 1;
```

2. 定义请求函数

```java
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
```

---

# 动态申请权限

3. 检查授权结果

```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
    @NonNull int[] grantResults) {
  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  if (requestCode != REQUEST_CAMERA_PERMISSION || grantResults.length == 0) {
    return;
  }
  if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
    Toast.makeText(this, "未授权使用摄像头", Toast.LENGTH_LONG).show();
    return;
  }
  // 授权成功，初始化摄像头
  initCamera();
}
```

4. 发起检查摄像头权限

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  ...
  requestCameraPermission();
}
```
---

# 布局文件
activity_main.xml


```xml 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:id="@+id/main"
  android:background="@color/black"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".TakePhotoActivity">
  <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <!-- 拍照结果 -->
    <ImageView
      android:id="@+id/img_result"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      android:layout_weight="1" />
```

---

# 布局文件
activity_main.xml（续）

```xml
    <!-- 实时预览 -->
    <TextureView
      android:id="@+id/texture_preview"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      android:layout_weight="1" />
  </LinearLayout>
  <!-- 拍照按钮 -->
  <ImageView
    android:id="@+id/img_take_photo"
    android:src="@drawable/icon_take_photo"
    android:layout_width="80dp"
    android:layout_height="80dp"
    android:layout_marginBottom="50dp"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true" />
</RelativeLayout>
```

---

# 初始化摄像头

```java
public class MainActivity extends AppCompatActivity {

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
```

---

# 初始化摄像头

```java
  private void initCamera() {
    Log.d(TAG, "初始化摄像头");
    mPreview.setSurfaceTextureListener(new SurfaceTextureListener() {
      @Override
      @RequiresPermission(permission.CAMERA)
      public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) { openCamera(); }

      @Override
      public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) { return true; }

      @Override
      public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {}

      @Override
      public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {}
    });

    mTakePhotoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        captureStillImage();
      }
    });
  }

```

---

# 打开摄像头

```java
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
      Log.e(TAG, "打开摄像头失败");
    }
  }
```

---

# 绘制预览图片

```java
  private void createCameraPreviewSession() {
    try {
      SurfaceTexture texture = mPreview.getSurfaceTexture();
      texture.setDefaultBufferSize(640, 480);
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
```

---

# 拍照

```java
  private void captureStillImage() {
    if (mCameraDevice == null) {
      return;
    }

    Bitmap image = mPreview.getBitmap();
    mResultImage.setImageBitmap(image);
  }
```

---

# 回收资源

```java
  @Override
  protected void onDestroy() {
    if (mCameraDevice != null) {
      mCameraDevice.close();
    }
    super.onDestroy();
  }
}
```

---

# 效果图

<img src="/android-camera.jpg" class="h-[40vh]" />
