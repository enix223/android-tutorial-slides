
## TextView

- TextView是一个用于显示文本的控件。它可以显示单行或多行文本，并且支持丰富的文本样式和格式。

<div class="flex flex-col items-center justify-center">
    <img src="/text-view-1.png" width="680"/>
</div>

---

```xml 
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello, World!"
        android:textSize="20sp"
        android:textColor="#FF0000" />
        
</LinearLayout>
```

- 在Java代码中，可以通过以下方式访问和修改TextView：

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        TextView textView = findViewById(R.id.textView);
        textView.setText("New Text");
        textView.setTextSize(24);
        textView.setTextColor(Color.BLUE);
  }
}
```

---

- 也可以通过下面这种方式创建布局文件

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-1.gif" />
</div>

---

- 至少要指定两个方向，然后就能随意拖动控件位置

<div class="flex flex-col items-center justify-center">
    <img src="/constraint-layout-2.gif" width="500"/>
</div>

---

<div class="flex flex-col items-center justify-center">
    <img src="/text-view-2.png"  width="200"/>
</div>


<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：运用这些属性来写一个自己的TextView </div>