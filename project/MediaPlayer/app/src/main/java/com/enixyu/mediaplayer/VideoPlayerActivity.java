package com.enixyu.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class VideoPlayerActivity extends AppCompatActivity implements OnClickListener,
    SurfaceHolder.Callback {

  private MediaPlayer mPlayer = null;
  private SurfaceView sfv_show;
  private SurfaceHolder surfaceHolder;
  private Button btn_start;
  private Button btn_pause;
  private Button btn_stop;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_player);

    sfv_show = findViewById(R.id.sfv_show);
    btn_start = findViewById(R.id.btn_start);
    btn_pause = findViewById(R.id.btn_pause);
    btn_stop = findViewById(R.id.btn_stop);

    btn_start.setOnClickListener(this);
    btn_pause.setOnClickListener(this);
    btn_stop.setOnClickListener(this);

    //初始化SurfaceHolder类，SurfaceView的控制器
    surfaceHolder = sfv_show.getHolder();
    surfaceHolder.addCallback(this);
    surfaceHolder.setFixedSize(320, 220);   //显示的分辨率,不设置为视频默认
  }

  @Override
  public void onClick(View v) {
    if (mPlayer == null) {
      return;
    }
    if (v.getId() == R.id.btn_start) {
      // 开始播放
      mPlayer.start();
    } else if (v.getId() == R.id.btn_pause) {
      // 暂停
      mPlayer.pause();
    } else if (v.getId() == R.id.btn_stop) {
      // 停止播放
      mPlayer.stop();
      try {
        mPlayer.prepare();
      } catch (IOException e) {
        Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    mPlayer = MediaPlayer.create(this, R.raw.video_test);
    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    mPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
  }

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

  }
}