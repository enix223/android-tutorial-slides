# 属性动画实现方式

* 核心类

1. ValueAnimator - 数值变化器

2. ObjectAnimator - 对象属性动画器

3. AnimatorSet - 动画集合

4. TypeEvaluator - 类型估值器

5. TimeInterpolator - 时间插值器

---

# 实现方式:

1. 基本的ObjectAnimator使用

```java
// Java
public class PropertyAnimationActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        View targetView = findViewById(R.id.target_view);
        
        // 1. 透明度动画
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(targetView, "alpha", 1f, 0f, 1f);
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(0); // 重复次数. ValueAnimator.INFINITE 无限循环
        
        // 2. 平移动画（实际改变translationX属性）
        ObjectAnimator translateXAnim = ObjectAnimator.ofFloat(targetView, "translationX", 0f, 200f, 0f);
        translateXAnim.setDuration(1200);
        translateXAnim.setInterpolator(new BounceInterpolator());
        
        // 3. 缩放动画（实际改变scaleX和scaleY属性）
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(targetView, "scaleX", 1f, 2f, 1f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(targetView, "scaleY", 1f, 2f, 1f);
        
```

---

```java  

        // 4. 旋转动画（实际改变rotation属性）
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(targetView, "rotation", 0f, 360f);
        rotateAnim.setDuration(1200);

        
        // 5. 背景色动画（需要自定义View支持或使用ArgbEvaluator）
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(targetView, "backgroundColor", 
            Color.RED, Color.BLUE, Color.GREEN);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(1500);
        
        // 启动单个动画
        translateXAnim.start();
        
        // 或者使用AnimatorSet组合动画
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, scaleXAnim, scaleYAnim);
        // set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim); // 顺序执行
        set.setDuration(1000);
        set.start();
    }
}
```

---

2. ValueAnimator的灵活使用

```java
// 使用ValueAnimator控制任何数值变化
ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
valueAnimator.setDuration(1000);
valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        // 使用这个值做任何你想做的事
        view.setAlpha(value);
        view.setTranslationX(value * 100);
    }
});
valueAnimator.start();

// 使用TypeEvaluator自定义估值器
ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), 
    Color.RED, Color.BLUE);
colorAnimator.setDuration(1000);
colorAnimator.addUpdateListener(animation -> {
    view.setBackgroundColor((int) animation.getAnimatedValue());
});
colorAnimator.start();
```

---

3. ViewPropertyAnimator（简洁API）

```java
// ViewPropertyAnimator提供了更简洁的链式调用
view.animate()
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
```

---


# XML方式定义属性动画

```xml
<!-- res/animator/property_anim.xml -->
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:ordering="together">
    
    <objectAnimator
        android:propertyName="translationX"
        android:duration="1000"
        android:valueFrom="0"
        android:valueTo="200"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/accelerate_decelerate" />
        
    <objectAnimator
        android:propertyName="alpha"
        android:duration="1000"
        android:valueFrom="1"
        android:valueTo="0"
        android:valueType="floatType" />
</set>
```

```java
// 加载XML中的属性动画
AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property_anim);
set.setTarget(view);
set.start();
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-animation-4.gif" width="300"/>
</div>