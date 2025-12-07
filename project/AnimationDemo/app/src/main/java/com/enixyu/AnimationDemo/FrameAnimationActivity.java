package com.enixyu.AnimationDemo;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import java.util.Objects;

public class FrameAnimationActivity extends AppCompatActivity {

  // 预定义的资源ID数组（编译时优化）
  private static final int[] FRAME_RESOURCES = {
      R.drawable.img00, R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04,
      R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09,
      R.drawable.img10, R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14,
      R.drawable.img15, R.drawable.img16, R.drawable.img17, R.drawable.img18, R.drawable.img19,
      R.drawable.img20, R.drawable.img21, R.drawable.img22, R.drawable.img23, R.drawable.img24
  };

  private ImageView animationViewXml, animationViewJava;
  private AnimationDrawable animationXmlDrawable, animationJavaDrawable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_frame_animation);

    initViews();
  }

  private void initViews() {
    // 找到ImageView
    animationViewXml = findViewById(R.id.animation_view);
    animationViewJava = findViewById(R.id.animation_view2);

    // 初始化XML动画
    initXmlAnimation();

    // 初始化Java动画
    initJavaAnimation();

    // 设置按钮点击事件
    setupButtonListeners();
  }

  /**
   * 初始化XML动画（最简单的方式）
   */
  private void initXmlAnimation() {
    // 设置XML动画资源
    animationViewXml.setImageResource(R.drawable.frame_animation);
    animationXmlDrawable = (AnimationDrawable) animationViewXml.getDrawable();

    if (animationXmlDrawable != null) {
      animationXmlDrawable.setOneShot(false); // 循环播放
    }
  }

  /**
   * 初始化Java动画（简化版）
   */
  private void initJavaAnimation() {
    // 创建动画对象
    animationJavaDrawable = new AnimationDrawable();

    // 添加帧（简化：只处理成功的情况）
    for (int resId : FRAME_RESOURCES) {
      // 使用安全的getDrawable方法
      animationJavaDrawable.addFrame(
          Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), resId, getTheme())),
          150 // 每帧150毫秒，更慢一些方便观察
      );
    }

    // 设置动画属性
    animationJavaDrawable.setOneShot(false); // 循环播放

    // 应用到ImageView
    animationViewJava.setImageDrawable(animationJavaDrawable);
  }

  private void setupButtonListeners() {
    // XML动画控制
    findViewById(R.id.btn_start_xml).setOnClickListener(v -> {
      if (animationXmlDrawable != null && !animationXmlDrawable.isRunning()) {
        animationXmlDrawable.start();
      }
    });

    findViewById(R.id.btn_stop_xml).setOnClickListener(v -> {
      if (animationXmlDrawable != null && animationXmlDrawable.isRunning()) {
        animationXmlDrawable.stop();
      }
    });

    // Java动画控制
    findViewById(R.id.btn_start_java).setOnClickListener(v -> {
      if (animationJavaDrawable != null && !animationJavaDrawable.isRunning()) {
        animationJavaDrawable.start();
      }
    });

    findViewById(R.id.btn_stop_java).setOnClickListener(v -> {
      if (animationJavaDrawable != null && animationJavaDrawable.isRunning()) {
        animationJavaDrawable.stop();
      }
    });
  }

  @Override
  protected void onPause() {
    super.onPause();
    // 简单停止动画
    if (animationXmlDrawable != null) {
      animationXmlDrawable.stop();
    }
    if (animationJavaDrawable != null) {
      animationJavaDrawable.stop();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 简单清理
    if (animationXmlDrawable != null) {
      animationXmlDrawable.stop();
    }
    if (animationJavaDrawable != null) {
      animationJavaDrawable.stop();
    }
  }
}