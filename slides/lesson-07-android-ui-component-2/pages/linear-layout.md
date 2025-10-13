# LinearLayout

| 属性 | 作用 | 说明 |
|-|-|-|
| android:orientation | 布局方向 | 垂直排列是`vertical`，水平排列是`horizontal` |
| android:gravity | 父组件对齐方式 | 控制子组件的对齐方式，垂直方向（`top`、`center_vertical`、`bottom`）水平方向（`left`、`right`、`center_horizontal`），水平和垂直方向 (`center`)，gravity默认是`top` + `left` |

<p />

* 当父组件位LinearLayout时，子组件新增的属性:

| 属性 | 作用 | 说明 |
|-|-|-|
| android:layout_gravity | 子组件对齐方式 | 取值跟android:gravity一致 |
| android:layout_weight | 控制子组件的比例 | 例如子组件A的`layout_weight=1`, 子组件B的`layout_weight=2`，则A与B的比例为1比2 |

---

# gravity的使用 - 垂直排列 + 上对齐
orientation=veritcal, gravity = top|...

<img src="/linearlayout-gravity-top.drawio.png" class="h-[80%]" />

---

# gravity的使用 - 垂直排列 + 垂直居中
orientation=vertical, gravity = center_vertical|...

<img src="/linearlayout-gravity-center-vertical.drawio.png" class="h-[80%]" />

---

# gravity的使用 - 垂直排列 + 下对齐
orientation=vertical, gravity = bottom|...

<img src="/linearlayout-gravity-bottom.drawio.png" class="h-[80%]" />

---

# gravity的使用 - 水平排列 + 左对齐
orientation=vertical, gravity = left|...

<img src="/linearlayout-gravity-left.drawio.png" class="h-[80%]" />

---

# gravity的使用 - 水平排列 + 右对齐
orientation=vertical, gravity = right|...

<img src="/linearlayout-gravity-right.drawio.png" class="h-[80%]" />

---

# gravity的使用 - 水平排列 + 水平居中对齐
orientation=vertical, gravity = center_horizontal|...

<img src="/linearlayout-gravity-center-horizontal.drawio.png" class="h-[80%]" />

---

# 控件对齐方式举例

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

# 以上按钮效果如图：

<div class="flex flex-col items-center justify-center">
    <img src="/linear-layout-1.png" width="700"/>
</div>

---

# 设置控件大小比重weight举例

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

# 效果如图所示

<div class="flex flex-col items-center justify-center">
    <img src="/linear-layout-2.png" width="700"/>
</div>