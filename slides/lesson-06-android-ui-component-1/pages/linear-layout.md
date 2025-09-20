
## LinearLayout

- android:orientation 垂直排列是vertical，水平排列是horizontal

- android:layout_gravity 对齐方式，垂直方向（top、center_vertical、bottom）水平方向（left、right、center_horizontal）

- android:layout_weight 来指定控件的大小，此时可以将宽度设定为0dp

- 注意：如果LinearLayout的排列方向是horizontal，控件宽度不能为match_parent，否则单独一个控件就会将整个水平方向占满，如果LinearLayout的排列方向是vertical，控件高度不能为为match_parent

- 注意：当LinearLayout的排列方向是horizontal时，只有垂直方向上的对齐方式才会生效，同理，是vertical时，只有 水平方向上的对齐方式才会生效

---

## 控件对齐方式举例：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="horizontal"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
    <Button
            android:id="@+id/button1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="top"
            android:text="Button 1" />
    <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:text="Button 2" />
    <Button
            android:id="@+id/button3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom"
            android:text="Button 3" />
</LinearLayout>
```

--- 

## 以上按钮效果如图：
<div class="flex flex-col items-center justify-center">
    <img src="/linear-layout-1.png" width="700"/>
</div>

---

## 设置控件大小比重weight举例：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="horizontal"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
    <EditText
              android:id="@+id/input_message"
              android:layout_width="0dp"
              android:layout_height="wrap_content"
              android:layout_weight="2"
              android:hint="Type something"
              />
    <Button
            android:id="@+id/send"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Send"
            />
</LinearLayout>

```

--- 

## 效果如图所示：
<div class="flex flex-col items-center justify-center">
    <img src="/linear-layout-2.png" width="700"/>
</div>