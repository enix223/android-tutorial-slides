package com.enixyu.AnimationDemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationActivity extends AppCompatActivity {

  private View targetView, targetView2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_animation);

    targetView = findViewById(R.id.target_view);
    Button btnAlphaXml = findViewById(R.id.btn_alpha_xml);
    Button btnRotateXml = findViewById(R.id.btn_rotate_xml);
    Button btnScaleXml = findViewById(R.id.btn_scale_xml);
    Button btnTranslateXml = findViewById(R.id.btn_translate_xml);

    targetView2 = findViewById(R.id.target_view2);
    Button btnAlphaJava = findViewById(R.id.btn_alpha_java);
    Button btnRotateJava = findViewById(R.id.btn_rotate_java);
    Button btnScaleJava = findViewById(R.id.btn_scale_java);
    Button btnTranslateJava = findViewById(R.id.btn_translate_java);

    // 加载单个动画
    Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
    Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
    Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
    Animation translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim);

    // 加载组合动画
    Animation setAnim = AnimationUtils.loadAnimation(this, R.anim.set_anim);

    // 启动动画
    targetView.startAnimation(setAnim);

    // 创建透明度动画
    AlphaAnimation alphaAnimJava = new AlphaAnimation(1.0f, 0.0f);
    alphaAnimJava.setDuration(1000);
    alphaAnimJava.setInterpolator(new AccelerateDecelerateInterpolator());
    alphaAnimJava.setFillAfter(true);

    // 创建平移动画
    TranslateAnimation translateAnimJava = new TranslateAnimation(
        Animation.RELATIVE_TO_SELF, 0f,   // fromXType, fromXValue
        Animation.RELATIVE_TO_SELF, 1f,   // toXType, toXValue
        Animation.RELATIVE_TO_SELF, 0f,   // fromYType, fromYValue
        Animation.RELATIVE_TO_SELF, 0f    // toYType, toYValue
    );
    translateAnimJava.setDuration(800);
    translateAnimJava.setInterpolator(new AccelerateDecelerateInterpolator());
    translateAnimJava.setFillAfter(true);

    // 创建旋转动画
    RotateAnimation rotateAnimJava = new RotateAnimation(
        0, 360,                            // fromDegrees, toDegrees
        Animation.RELATIVE_TO_SELF, 0.5f,  // pivotXType, pivotXValue
        Animation.RELATIVE_TO_SELF, 0.5f   // pivotYType, pivotYValue
    );
    rotateAnimJava.setDuration(1200);
    rotateAnimJava.setRepeatCount(Animation.INFINITE);
    rotateAnimJava.setRepeatMode(Animation.RESTART);

    //创建缩放动画
    ScaleAnimation scaleAnimJava = new ScaleAnimation(
        1.0f,  // fromXScale: 初始X轴缩放比例
        2.0f,  // toXScale:   结束X轴缩放比例
        1.0f,  // fromYScale: 初始Y轴缩放比例
        2.0f,  // toYScale:   结束Y轴缩放比例
        0.5f,  // pivotX:     缩放中心点X坐标（相对自身50%）
        0.5f   // pivotY:     缩放中心点Y坐标（相对自身50%）
    );
    scaleAnimJava.setDuration(600);
    scaleAnimJava.setFillAfter(true);

    // 组合动画
    AnimationSet animationSetJava = new AnimationSet(true);
    animationSetJava.addAnimation(new AlphaAnimation(0.0f, 1.0f));
    animationSetJava.addAnimation(new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,   // fromXType, fromXValue
        Animation.RELATIVE_TO_SELF, 0f,   // toXType, toXValue
        Animation.RELATIVE_TO_SELF, -1f,   // fromYType, fromYValue
        Animation.RELATIVE_TO_SELF, 0f   ));
    animationSetJava.addAnimation(new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f));
    animationSetJava.setDuration(1000);

    // 启动动画
    targetView2.startAnimation(animationSetJava);

    btnAlphaXml.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView.startAnimation(alphaAnim);
      }
    });
    btnRotateXml.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView.startAnimation(rotateAnim);
      }
    });
    btnScaleXml.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView.startAnimation(scaleAnim);
      }
    });
    btnTranslateXml.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView.startAnimation(translateAnim);
      }
    });

    btnAlphaJava.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView2.startAnimation(alphaAnimJava);
      }
    });
    btnRotateJava.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView2.startAnimation(rotateAnimJava);
      }
    });
    btnScaleJava.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView2.startAnimation(scaleAnimJava);
      }
    });
    btnTranslateJava.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        targetView2.startAnimation(translateAnimJava);
      }
    });
  }

  @Override
  protected void onPause() {
    super.onPause();
    // 建议添加：页面不可见时停止动画
    targetView.clearAnimation();
    targetView2.clearAnimation();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    targetView.clearAnimation();
    targetView2.clearAnimation();
  }
}
