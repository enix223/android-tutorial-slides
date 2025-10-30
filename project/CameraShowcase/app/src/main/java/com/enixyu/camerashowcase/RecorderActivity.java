package com.enixyu.camerashowcase;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;

public class RecorderActivity extends AppCompatActivity {

  private final String TAG = getClass().getSimpleName();
  private final int REQUEST_CODE_RECORDING = 2;
  private ImageView mImageRecording;
  private MediaRecorder mediaRecorder;
  private MediaPlayer mediaPlayer;
  private boolean mRecording = false;
  private boolean mPlaying = false;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recorder);
    mImageRecording = findViewById(R.id.img_recording);
    mediaRecorder = new MediaRecorder();
    mediaPlayer = new MediaPlayer();
    requestRecordingPermission();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
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

  private void requestRecordingPermission() {
    int grantResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
    if (grantResult == PackageManager.PERMISSION_GRANTED) {
      initRecorder();
      return;
    }

    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
        REQUEST_CODE_RECORDING);
  }

  private void initRecorder() {
    try {
      mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
      mediaRecorder.setOutputFile(getRecordingFile());
      mediaRecorder.prepare();

      mImageRecording.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          if (mRecording) {
            Log.d(TAG, "结束录音");
            mediaRecorder.stop();
            playBack();
            mImageRecording.setImageResource(R.drawable.icon_microphone);
          } else {
            Log.d(TAG, "开始录音");
            stopPlay();
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

  private void playBack() {
    try {
      Log.d(TAG, "开始回放");
      mediaPlayer.setDataSource(getRecordingFile());
      mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
          mPlaying = false;
          Log.d(TAG, "回放结束");
        }
      });
      mediaPlayer.prepare();
      mediaPlayer.start();
      mPlaying = true;
    } catch (IOException e) {
      Log.e(TAG, "回放失败", e);
      showMessage("回放失败");
    }
  }

  private void stopPlay() {
    if (mPlaying) {
      mediaPlayer.stop();
    }
  }

  private String getRecordingFile() {
    return getExternalFilesDir(null) + "/my_recording.3gp";
  }

  private void showMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }
}
