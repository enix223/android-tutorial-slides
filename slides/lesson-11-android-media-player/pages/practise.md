# 课堂作业

根据课堂ppt完成一个音频播放器

* 实现播放mp3音频
* 暂停/停止播放
* 进度条显示播放进度

资源下载地址，存放在`res/raw`文件夹中

* [http://course.cloudesk.top/android-tutorial-slides/lesson-11/merry_christmas_mr_lawrence.mp3](http://course.cloudesk.top/android-tutorial-slides/lesson-11/merry_christmas_mr_lawrence.mp3)

---

# 课堂作业

AudioPlayerActivity

```java
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
```

---

# 课堂作业

```java
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
```

---

# 课堂作业

```java
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
```

---

# 课堂作业

```java
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
```

---

# 课堂作业

activity_audio_player.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:gravity="center"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical">

  <SeekBar
    android:id="@+id/progress_bar"
    android:thumbTint="@color/purple_700"
    android:progressTint="@color/purple_200"
    android:foregroundTint="@color/purple_200"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

---

# 课堂作业

```xml
  <LinearLayout
    android:layout_marginTop="40dp"
    android:gravity="center"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <Button
      android:id="@+id/btn_play"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="播放" />
    <Button
      android:id="@+id/btn_pause"
      android:layout_marginStart="10dp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="暂停" />
    <Button
      android:id="@+id/btn_stop"
      android:layout_marginStart="10dp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="停止" />
  </LinearLayout>
</LinearLayout>
```