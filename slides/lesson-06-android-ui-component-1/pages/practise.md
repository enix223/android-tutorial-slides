# 课堂练习

练习使用TextView和Button和事件处理

* 创建一个Activity: <span class="text-blue-700">MainActivity</span>
* 在AndroidManifest.xml中注册这个activity。
* 给MainActivity添加布局LinearLayout。
* 在LinearLayout中添加一个Button和TextView
* 注册一个按钮事件，点击按钮后，设置TextView的文本为: "Hello"

<div class="text-2xl mt-5">课堂PPT</div>
<div class="mt-3">
    <QRCode
        value="http://course.cloudesk.top"
        :width="180"
        :height="180"
        color=""
        image=""
    />
</div>

---

# 课堂练习

效果展示

<div class="flex flex-row">
    <div class="flex flex-1">
        <img src="/homework-1.png" class="w-[40%]"/>
    </div>
    <div class="flex flex-1">
        <ul>
            <li>LinearLayout容器布局方式(android:gravity): center</li>
            <li>LinearLayout背景颜色(android:orientation): vertical</li>
            <li>"你好"的字体颜色(android:textColor): #FFFFFFFF</li>
            <li>"你好"的字体颜色(android:textColor): #FFFFFFFF</li>
            <li>"你好"的ID(android:id): @+id/text_view</li>
            <li>"按钮"背景颜色(android:backgroundTint): #FFFFFFFF</li>
            <li>"按钮"的文字颜色(android:textColor): #FFE7564F</li>
            <li>"按钮"的外边距(android:layout_margin): 20dp</li>
            <li>"按钮"的id(android:id): @+id/button</li>
        </ul>
    </div>
</div>

---

# 课堂练习
代码实现部分

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // -------- 增加如下内容 ----------- //
        // 根据ID获取按钮的对象
        Button button = findViewById(R.id.button);

        // 注册按钮点击事件
        button.setOnClickListener((v) -> {
            TextView tv = findViewById(R.id.text_view);
            tv.setText("你好");
        });
    }
}
```