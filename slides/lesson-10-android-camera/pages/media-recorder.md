# MediaRecorder录音

* 申请访问麦克风权限
* 构造`MediaRecorder`
* 配置声音源: `MediaRecorder.AudioSource.MIC`
* 配置输出格式: `MediaRecorder.OutputFormat.THREE_GPP`
* 配置编码器: `MediaRecorder.AudioEncoder.AMR_NB`
* 配置输出文件的地址

---

# 录音权限申请

1. 声明录音权限

```xml
<uses-feature android:name="android.hardware.microphone" android:required="true" />

<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

2. 动态请求权限

```java
private void requestRecordingPermission() {
  int grantResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
  if (grantResult == PackageManager.PERMISSION_GRANTED) {
    initRecorder();
    return;
  }

  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_RECORDING);
}
```

---

# 录音权限申请

3. 检查请求结果

```java
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  if (requestCode != REQUEST_CODE_RECORDING || grantResults.length == 0) {
    return;
  }
  if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
    showMessage("未授权使用麦克风");
    return;
  }
  initRecorder();
}
```

---

# 录音

```java
private void initRecorder() {
  try {
    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    mediaRecorder.setOutputFile(getExternalFilesDir(null) + "/my_recording.3gp");
    mediaRecorder.prepare();

    mImageRecording.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mRecording) {
          mediaRecorder.stop();
          mImageRecording.setImageResource(R.drawable.icon_microphone);
        } else {
          mediaRecorder.start();
          mImageRecording.setImageResource(R.drawable.icon_stop);
        }
        mRecording = !mRecording;
      }
    });
  } catch (Exception e) {
    Log.e(TAG, "录音失败", e);
    showMessage("录音失败");
  }
}
```
