package com.enixyu.AnimationDemo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PropertyAnimationActivity extends AppCompatActivity {

  private int initialColor;
  private View targetView;
  private TextView tvInfo;
  private  ObjectAnimator alphaAnim, translateXAnim, scaleXAnim, scaleYAnim, rotateAnim, colorAnim;
  private AnimatorSet set;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_property_animation);

    targetView = findViewById(R.id.target_view);
    tvInfo = findViewById(R.id.tv_info);

    initialColor = ((ColorDrawable) targetView.getBackground()).getColor();

    initAnimation();

    setupButtonListeners();

  }

  private void initAnimation() {
    alphaAnim = ObjectAnimator.ofFloat(targetView, "alpha", 1f, 0f, 1f);
    alphaAnim.setDuration(1000);
    alphaAnim.setRepeatCount(0);

    // 2. 平移动画（实际改变translationX属性）
    translateXAnim = ObjectAnimator.ofFloat(targetView, "translationX", 0f, 200f, 0f);
    translateXAnim.setDuration(1200);
    translateXAnim.setInterpolator(new BounceInterpolator());

    // 3. 缩放动画（实际改变scaleX和scaleY属性）
    scaleXAnim = ObjectAnimator.ofFloat(targetView, "scaleX", 1f, 2f, 1f);
    scaleXAnim.setDuration(1000);
    scaleYAnim = ObjectAnimator.ofFloat(targetView, "scaleY", 1f, 2f, 1f);
    scaleYAnim.setDuration(1000);

    // 4. 旋转动画（实际改变rotation属性）
    rotateAnim = ObjectAnimator.ofFloat(targetView, "rotation", 0f, 360f);
    rotateAnim.setDuration(1200);


    // 5. 背景色动画（需要自定义View支持或使用ArgbEvaluator）
    colorAnim = ObjectAnimator.ofInt(targetView, "backgroundColor",
        Color.RED, Color.BLUE, Color.GREEN);
    colorAnim.setEvaluator(new ArgbEvaluator());
    colorAnim.setDuration(1500);

    set = new AnimatorSet();
    set.playTogether(alphaAnim, scaleXAnim, scaleYAnim);
    // set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim); // 顺序执行
    set.setDuration(1000);
  }

  private void setupButtonListeners() {
    // 透明度动画
    findViewById(R.id.btn_alpha).setOnClickListener(v -> {
      showInfo("透明度动画: 1 → 0 → 1");
      if (alphaAnim.isRunning()) {
        alphaAnim.cancel();
      }
      alphaAnim.start();
    });

    // 平移动画
    findViewById(R.id.btn_translate).setOnClickListener(v -> {
      showInfo("平移动画: X轴移动200像素");
      if (translateXAnim.isRunning()) {
        translateXAnim.cancel();
      }
      translateXAnim.start();
    });

    // 缩放动画
    findViewById(R.id.btn_scale).setOnClickListener(v -> {
      showInfo("缩放动画: 放大2倍再恢复");
      if (scaleXAnim.isRunning()) {
        scaleXAnim.cancel();
      }
      scaleXAnim.start();
    });

    // 旋转动画
    findViewById(R.id.btn_rotate).setOnClickListener(v -> {
      showInfo("旋转动画: 旋转360度");
      if (rotateAnim.isRunning()) {
        rotateAnim.cancel();
      }
      rotateAnim.start();
    });

    findViewById(R.id.btn_color).setOnClickListener(v-> {
      showInfo("背景色动画");
      if (colorAnim.isRunning()) {
        colorAnim.cancel();
      }
      colorAnim.start();
    });

    // 组合动画
    findViewById(R.id.btn_combo).setOnClickListener(v -> {
      showInfo("组合动画");
      if (set.isRunning()) {
        set.cancel();
      }
      set.start();
    });

    // ValueAnimator示例
    findViewById(R.id.btn_value).setOnClickListener(v -> {
      showInfo("ValueAnimator: 自定义数值变化");
      ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
      animator.setDuration(1000);

      animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
          float value = (float) animation.getAnimatedValue();

          // 使用这个数值做任何事
          targetView.setAlpha(value);
          targetView.setTranslationX(value * 100); // 随着数值移动

          // 更新信息显示
          tvInfo.setText("当前值: " + String.format("%.2f", value));
        }
      });

      animator.start();

      // 使用TypeEvaluator自定义估值器
      ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
          Color.RED, Color.BLUE);
      colorAnimator.setDuration(1000);
      colorAnimator.addUpdateListener(animation -> {
        targetView.setBackgroundColor((int) animation.getAnimatedValue());
      });
      colorAnimator.start();
    });

    // 重置按钮
    findViewById(R.id.btn_reset).setOnClickListener(v -> {
      resetView();
      showInfo("已重置所有属性");
    });

    // 简洁api
    findViewById(R.id.btn_api).setOnClickListener(v -> {
      targetView.animate()
          .alpha(0.5f)
          .translationX(100f)
          .translationY(50f)
          .scaleX(1.5f)
          .scaleY(1.5f)
          .rotation(45f)
          .setDuration(1000)
          .setInterpolator(new BounceInterpolator())
          .withStartAction(() -> {
            // 动画开始前执行
            Log.d("Anim", "开始");
          })
          .withEndAction(() -> {
            // 动画结束后执行
            Log.d("Anim", "结束");
          })
          .start();
    });

    // xml
    findViewById(R.id.btn_xml).setOnClickListener(v -> {
      showInfo("xml");

      AnimatorSet xmlSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property_anim);
      xmlSet.setTarget(targetView);
      xmlSet.start();
    });
  }

  private void showInfo(String message) {
    if (tvInfo != null) {
      tvInfo.setText(message);
    }
    Log.d("PropertyAnim", message);
  }

  private void resetView() {
    // 重置所有属性
    targetView.setAlpha(1f);
    targetView.setTranslationX(0f);
    targetView.setTranslationY(0f);
    targetView.setScaleX(1f);
    targetView.setScaleY(1f);
    targetView.setRotation(0f);
    targetView.setBackgroundColor(initialColor);
  }

  @Override
  protected void onPause() {
    super.onPause();
    resetView();
  }
}