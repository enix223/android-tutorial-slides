# 🎵 什么是 MediaPlayer

- MediaPlayer 是 Android 提供的 多媒体播放接口，用于播放 音频、视频 以及 网络流媒体。

- 它属于 android.media 包，是 Android 系统内置的基础多媒体播放类。

- 🧱 1. MediaPlayer（基础播放引擎）

- 可播放多种媒体源：

  本地文件（如 /sdcard/Music/song.mp3）

  应用资源文件（如 R.raw.music）

  网络音频或视频流（如 https://example.com/video.mp4）

- 提供完整的播放控制功能：

  播放、暂停、停止、跳转、循环播放

  音量调节、监听播放状态

- 📌 类名：android.media.MediaPlayer

---

# 相关方法详解

- 获得MediaPlayer实例：

  可以直接new或者调用create方法创建：

```java 
MediaPlayer mp = new MediaPlayer();
MediaPlayer mp = MediaPlayer.create(this, R.raw.test);  //无需再调用setDataSource
```

- 另外create还有这样的形式： create(Context context, Uri uri, SurfaceHolder holder) 通过Uri和指定 SurfaceHolder 【抽象类】 创建一个多媒体播放器

- 设置播放文件

```java
//①raw下的资源：
MediaPlayer.create(this, R.raw.test);

//②本地文件路径：
mp.setDataSource("/sdcard/test.mp3");

//③网络URL文件：
mp.setDataSource("http://www.xxx.com/music/test.mp3");
```

---

- 另外setDataSource()方法有多个，里面有这样一个类型的参数：FileDescriptor，在使用这个 API的时候，需要把文件放到res文件夹平级的assets文件夹里，然后使用下述代码设置DataSource：

```java
AssetFileDescriptor fileDescriptor = getAssets().openFd("rain.mp3");
m_mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(), fileDescriptor.getLength());
```

---

- 其他方法

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">参数类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">说明</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">示例值 / 返回值</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">getCurrentPosition()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">获取当前播放位置（毫秒）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int，如 12500</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">getDuration()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">获取媒体文件总时长（毫秒）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int，如 240000</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">getVideoHeight()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">获取视频高度（像素）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int，如 1080</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">getVideoWidth()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">获取视频宽度（像素）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int，如 1920</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">isLooping()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">是否循环播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">boolean，true / false</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">isPlaying()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">是否正在播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">boolean，true / false</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">pause()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">暂停播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">prepare()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">同步准备播放（会阻塞主线程）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">prepareAsync()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">异步准备播放（推荐使用）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">start()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">开始播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

</div>

---

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">参数类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">说明</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">示例值 / 返回值</div>
  </div>
  

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">stop()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">停止播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">release()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">释放 MediaPlayer 对象资源</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">reset()</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">重置 MediaPlayer 状态（可重新设置数据源）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">seekTo(int msec)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">跳转到指定播放位置（毫秒）</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">seekTo(60000)</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setAudioStreamType(int streamtype)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">int</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置流媒体类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">AudioManager.STREAM_MUSIC</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setDisplay(SurfaceHolder sh)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">SurfaceHolder</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置用于显示视频的 SurfaceHolder</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">无返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setLooping(boolean looping)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">boolean</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置是否循环播放</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setLooping(true)</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setOnBufferingUpdateListener(...)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">接口监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">网络流媒体缓冲监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">onBufferingUpdate()</div>
  </div>

</div>


---

<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">方法</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">参数类型</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">说明</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">示例值 / 返回值</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setOnCompletionListener(...)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">接口监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">播放完成监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">onCompletion()</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setOnErrorListener(...)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">接口监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">错误信息监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">onError()</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setOnVideoSizeChangedListener(...)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">接口监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">视频尺寸变化监听</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">onVideoSizeChanged()</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setScreenOnWhilePlaying(boolean screenOn)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">boolean</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">播放时是否保持屏幕常亮</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setScreenOnWhilePlaying(true)</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setVolume(float leftVolume, float rightVolume)</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">float, float</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">设置左右声道音量</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">setVolume(1.0f, 1.0f)</div>
  </div>

</div>

