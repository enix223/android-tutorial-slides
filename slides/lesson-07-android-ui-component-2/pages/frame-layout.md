# FrameLayout

- 帧布局，所有的控件都会默认摆放在布局的左上角
- 子组件类似扑克牌一样，一个一个层叠在一起，最后一个定义的组件会在顶端。
- 可以用于控制某个时间只显示某个子组件，其他子组件隐藏。
- 实现蒙层效果

<p />

* 当父组件为FrameLayout时，子组件新增的属性:

| 属性 | 作用 | 说明 |
|-|-|-|
| android:layout_gravity | 子组件对齐方式 | 取值跟LinearLayout的android:gravity一致，用于控制子组件在父组件的对齐方式 |

---

# FrameLayout使用

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

# FrameLayout效果图

<div class="flex flex-col items-center justify-center">
    <img src="/frame-layout.png" width="600"/>
</div>