# 帧动画实现方式

1. XML资源方式（推荐）
  res/drawable/frame_animation.xml:

```xml 
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false"
    android:visible="true">
     <!-- oneshot true:播放一次；false:循环播放 -->
    
    <item
        android:drawable="@drawable/img00"
        android:duration="100" /> <!-- 每帧持续时间(毫秒) -->
    <item
        android:drawable="@drawable/img01"
        android:duration="100" />
    <item
        android:drawable="@drawable/img02"
        android:duration="100" />
    <item
        android:drawable="@drawable/img03"
        android:duration="100" />
    <!-- 添加更多帧... -->
</animation-list>
```

---

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical"
  android:padding="20dp">

  <ImageView
    android:id="@+id/animation_view"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:layout_gravity="center"
    android:scaleType="fitCenter"/>


  <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <Button
      android:id="@+id/btn_start"
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="1"
      android:text="开始动画"/>
```

---

```xml

    <Button
      android:id="@+id/btn_stop"
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="1"
      android:text="停止动画"/>

  </LinearLayout>

</LinearLayout>
```

---

```java
// Java
public class MainActivity extends AppCompatActivity {
    private ImageView animationView;
    private AnimationDrawable animationDrawable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        
        animationView = findViewById(R.id.animation_view);
        animationView.setImageResource(R.drawable.frame_animation);
        animationDrawable = (AnimationDrawable) animationView.getDrawable();
        
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        
        btnStart.setOnClickListener(v -> startAnimation());
        btnStop.setOnClickListener(v -> stopAnimation());
    }
    
    private void startAnimation() {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }
```

---

```java

    private void stopAnimation() {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
    
    // 注意：避免在onCreate中直接start()，此时Drawable可能还未完全附加到窗口
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 可在此处开始动画
            // animationDrawable.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // 页面不可见时停止所有动画
        stopAnimation();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        stopAnimation();
        animationDrawable = null;
    }
}
```

---

2. 代码动态创建方式

```java
// 动态创建帧动画
AnimationDrawable animationJavaDrawable = new AnimationDrawable();

// 添加帧
animationJavaDrawable.addFrame(
    Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.img00, getTheme())),100);
animationJavaDrawable.addFrame(
    Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.img01, getTheme())),100);
animationJavaDrawable.addFrame(
    Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.img02, getTheme())),100);
animationJavaDrawable.addFrame(
    Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.img03, getTheme())),100);
//···添加更多

// 设置是否只播放一次
animationJavaDrawable.setOneShot(false);

// 应用到ImageView
imageView.setImageDrawable(animationJavaDrawable);
animationJavaDrawable.start();
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-animation-2.gif" width="300"/>
</div>

---

# 性能优化建议

* 控制帧数和图片大小：避免使用过多或过大的图片

* 及时释放资源：在Activity的onDestroy()中停止动画

* 使用WebP格式：减少图片文件大小