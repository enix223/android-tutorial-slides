# 补间动画实现方式

* 四种基本类型:

1. AlphaAnimation - 透明度动画

2. TranslateAnimation - 平移动画

3. ScaleAnimation - 缩放动画

4. RotateAnimation - 旋转动画

---

# 实现方式:

1. XML资源方式（推荐）
  
  透明度动画 (res/anim/alpha_anim.xml):

```xml 
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:fromAlpha="1.0"
    android:toAlpha="0.0"
    android:fillAfter="true"
    android:interpolator="@android:anim/accelerate_interpolator" />
  <!-- fillAfter = true 动画结束后保持状态 -->
```

  平移动画 (res/anim/translate_anim.xml):

```xml
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="800"
    android:fromXDelta="0%"
    android:toXDelta="100%"
    android:fromYDelta="0%"
    android:toYDelta="0%"
    android:fillAfter="true" />
```

---

  缩放动画 (res/anim/scale_anim.xml):
```xml
<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="600"
    android:fromXScale="1.0"
    android:toXScale="2.0"
    android:fromYScale="1.0"
    android:toYScale="2.0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fillAfter="true" />
  <!-- pivotY = 50% 缩放中心点 -->
```

  旋转动画 (res/anim/rotate_anim.xml):
```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1200"
    android:fromDegrees="0"
    android:toDegrees="360"
    android:pivotX="50%"
    android:pivotY="50%"
    android:repeatCount="infinite"
    android:repeatMode="restart" />
  <!-- repeatCount = infinite 无限循环 -->
```

---

  组合动画 (res/anim/set_anim.xml):

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:shareInterpolator="true"
    android:duration="1000">
    
    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0" />
        
    <translate
        android:fromYDelta="-100%"
        android:toYDelta="0%" />
        
    <scale
        android:fromXScale="0.5"
        android:toXScale="1.0"
        android:fromYScale="0.5"
        android:toYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%" />
</set>
```

---

```java
// Java - 加载XML动画
public class ViewAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        
        View targetView = findViewById(R.id.target_view);
       
        Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);  // 加载单个动画
        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        Animation translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
       
        Animation setAnim = AnimationUtils.loadAnimation(this, R.anim.set_anim);  // 加载组合动画
        
        targetView.startAnimation(setAnim);  // 启动动画
    }
    @Override
    protected void onPause() {
        super.onPause();
        targetView.clearAnimation();  // 停止动画（清除动画效果）
    }
    @Override
    protected void onDestroy() {
        super.onDestroy(); 
        targetView.clearAnimation();
    }
}
```

---

2. 代码动态创建方式

```java
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

```

---

```java

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
    Animation.RELATIVE_TO_SELF, 0f));
animationSetJava.addAnimation(new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f));
animationSetJava.setDuration(1000);

// 启动动画
view.startAnimation(animationSetJava);
```


---

<div class="flex flex-col items-center justify-center">
    <img src="/android-animation-3.gif"  width="300"/>
</div>