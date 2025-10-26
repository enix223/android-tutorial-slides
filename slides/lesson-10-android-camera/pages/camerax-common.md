
# CameraX 

- 添加依赖（build.gradle)

```groovy
dependencies {
    def camerax_version = "1.2.3"
    implementation "androidx.camera:camera-core:$camerax_version"
    implementation "androidx.camera:camera-camera2:$camerax_version"
    implementation "androidx.camera:camera-lifecycle:$camerax_version"
    implementation "androidx.camera:camera-view:$camerax_version"
}
```

---

- activity_main

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.camera.view.PreviewView
        android:id="@+id/previewView"
        android:layout_width="match_parent"
        android:layout_height="300dp" />
    
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_below="@id/previewView"
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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
```

---

```java

  private static final int REQUEST_CAMERA_PERMISSION = 100; 

  private PreviewView previewView;
  private ImageView imageView;
  private Button takePictureButton;

  private ImageCapture imageCapture; // 拍照用的 CameraX 对象

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    previewView = findViewById(R.id.previewView);
    imageView = findViewById(R.id.imageView);
    takePictureButton = findViewById(R.id.takePictureButton);

    if (checkCameraPermission()) {
      startCamera();
    } else {
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.CAMERA},
          REQUEST_CAMERA_PERMISSION);
    }

    takePictureButton.setOnClickListener(v -> takePhoto());
  }
```

---

```java

  private boolean checkCameraPermission() {
    return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED;
  }

  private void startCamera() {
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
        ProcessCameraProvider.getInstance(this);  // 获取 CameraProvider

    cameraProviderFuture.addListener(() -> {
      try {
        ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

        Preview preview = new Preview.Builder().build();  // 创建预览对象
        preview.setSurfaceProvider(previewView.getSurfaceProvider()); // 将预览绑定到 PreviewView

        imageCapture = new ImageCapture.Builder().build(); // 创建拍照对象

        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;  // 默认使用后置摄像头

        Camera camera = cameraProvider.bindToLifecycle(
            this, cameraSelector, preview, imageCapture);  // 绑定生命周期：Activity 生命周期内自动管理相机

      } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
      }
    }, getExecutor());
  }
```

---

```java

  private Executor getExecutor() { // 获取主线程 Executor CameraX 回调需要线程执行
    return ContextCompat.getMainExecutor(this);
  }

  private void takePhoto() {
    if (imageCapture == null) return;
    imageCapture.takePicture(getExecutor(), new ImageCapture.OnImageCapturedCallback() { // 调用 CameraX 拍照接口
      @Override
      public void onCaptureSuccess(@NonNull ImageProxy image) {
        // 将 Image 转 Bitmap
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        runOnUiThread(() -> {  // 在 UI 线程显示图片
          imageView.setVisibility(View.VISIBLE);
          imageView.setImageBitmap(bitmap);
        });
        image.close(); // 关闭 ImageProxy，释放资源
      }

      @Override
      public void onError(@NonNull ImageCaptureException exception) {
        exception.printStackTrace();
      }
    });
  }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-camera-2.gif" width="200"/>
</div>
