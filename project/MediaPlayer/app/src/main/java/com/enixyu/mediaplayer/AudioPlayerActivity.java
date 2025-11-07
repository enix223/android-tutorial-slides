package com.enixyu.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AudioPlayerActivity extends AppCompatActivity implements OnClickListener {

  private Button btn_play;
  private Button btn_pause;
  private Button btn_stop;
  private SeekBar progress_bar;
  private MediaPlayer mPlayer = null;
  private boolean isRelease = true;
  private boolean isPlaying = false;
  private Handler timeHandler = new Handler();
  private Runnable tickHandler = new Runnable() {
    @Override
    public void run() {
      if (isPlaying) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            progress_bar.setProgress(mPlayer.getCurrentPosition());
          }
        });
        timeHandler.postDelayed(this, 1000);
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio_player);

    btn_play = findViewById(R.id.btn_play);
    btn_pause = findViewById(R.id.btn_pause);
    btn_stop = findViewById(R.id.btn_stop);
    progress_bar = findViewById(R.id.progress_bar);

    btn_play.setOnClickListener(this);
    btn_pause.setOnClickListener(this);
    btn_stop.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.btn_play) {
      //开始播放
      if (isRelease) {
        mPlayer = MediaPlayer.create(this, R.raw.merry_christmas_mr_lawrence);
        isRelease = false;
      }
      progress_bar.setMax(mPlayer.getDuration());
      progress_bar.setProgress(mPlayer.getCurrentPosition());

      btn_play.setEnabled(false);
      btn_pause.setEnabled(true);
      btn_stop.setEnabled(true);
      timeHandler.post(tickHandler);
      mPlayer.start();
      isPlaying = true;
    } else if (v.getId() == R.id.btn_pause) {
      // 暂停播放
      mPlayer.pause();
      btn_play.setEnabled(true);
      btn_pause.setEnabled(false);
      btn_stop.setEnabled(false);
      isPlaying = false;
      timeHandler.removeCallbacks(tickHandler);
    }
    if (v.getId() == R.id.btn_stop) {
      // 停止播放
      mPlayer.reset();     //重置MediaPlayer
      mPlayer.release();   //释放MediaPlayer
      isRelease = true;
      isPlaying = false;
      btn_play.setEnabled(true);
      btn_pause.setEnabled(false);
      btn_stop.setEnabled(false);
      timeHandler.removeCallbacks(tickHandler);
      progress_bar.setProgress(0);
    }
  }
}