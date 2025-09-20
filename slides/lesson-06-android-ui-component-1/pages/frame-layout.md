
## FrameLayout

- 帧布局，所有的控件都会默认摆放在布局的左上角
- android:layout_gravity 对齐方式，垂直方向（top、center_vertical、bottom）水平方向（left、right、center_horizontal）

## 示例：
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
             android:layout_width="match_parent"
             android:layout_height="match_parent">
    <TextView
              android:id="@+id/textView"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="This is TextView"
              />
    <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button"
            />
</FrameLayout>
```

--- 

## 以上效果如图：
<div class="flex flex-col items-center justify-center">
    <img src="/frame-layout.png" width="700"/>
</div>