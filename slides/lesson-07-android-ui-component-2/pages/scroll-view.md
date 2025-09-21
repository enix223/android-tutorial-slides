
## ScrollView

- ScrollView称为滚动视图，当在一个屏幕的像素显示不下绘制的UI控件时，可以采用滑动的方式，使控件显示。

- 先看下ScrollView类的继承关系：

java.lang.Object
  ↳android.view.View
    ↳android.view.ViewGroup
      ↳android.widget.FrameLayout
        ↳android.widget.ScrollView

- 可以看出，ScrollView原来是一个FrameLayout的容器，不过在他的基础上添加了滚动，允许显示的比实际多的内容。
- 竖直滚动视图ScrollView

```xml
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
```

---

```xml
        <Button
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_marginTop="80dp"
            android:layout_marginBottom="80dp"
            android:gravity="center"
            android:text="内容一"
            android:textColor="#03A9F4"
            android:textSize="24sp" />

        ···

        <Button
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_marginTop="80dp"
            android:layout_marginBottom="80dp"
            android:gravity="center"
            android:text="内容五"
            android:textColor="#03A9F4"
            android:textSize="24sp" />
    </LinearLayout>
</ScrollView>
```

---

## 效果如图所示：
<div class="flex flex-col items-center justify-center">
    <img src="/scroll-view.gif" width="250"/>
</div>

---

## 水平滚动视图HorizontalScrollView
- 在实际使用时，我们也会遇到水平方向，控件超出屏幕的情况。这时就需要使用水平方向的滚动视图HorizontalScrollView。
- 将下面的xml加入到ScrollView的LinearLayout里面

```xml
<HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:layout_marginTop="20dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center_vertical"
            android:orientation="horizontal">

        <ImageView
            android:layout_width="120dp"
            android:layout_height="120dp"
            android:layout_margin="20dp"
            android:src="@mipmap/ic_launcher" />
            
        ···

    </LinearLayout>
</HorizontalScrollView>

```

---


## 效果如图所示：
<div class="flex flex-col items-center justify-center">
    <img src="/horizontal-scroll-view.gif" width="250"/>
</div>



