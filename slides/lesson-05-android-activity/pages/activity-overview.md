# Activity 概述

- Activity 是 Android 应用的四大组件之一

- 提供用户交互界面

- 每个屏幕通常对应一个 Activity

- 通过 setContentView() 设置布局

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```