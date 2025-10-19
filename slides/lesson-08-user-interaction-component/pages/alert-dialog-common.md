# 几种常用的对话框

- 接下来学习如何使用 AlertDialog 创建几种常见的对话框

    1. 普通对话框

    2. 普通列表对话框

    3. 单选列表对话框
    

---

1. 创建一个 空的 Android 项目 cn.twle.android.AlertDialog

2. 下载 /static/i/android/info.png 并放到 res/drawable 目录下 (或者直接用原有的ic_launcher)

3. 修改 MainActivity.java 创建一个普通的对话框

```java
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context ctx = MainActivity.this;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.info)
            .setTitle("系统提示")
            .setMessage("这是一个最普通的 AlertDialog,\n带有三个按钮，分别是取消，普通和确定");

        // 取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
```

---

```java
            }
        });

        // 确定按钮
        builder. setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
            }
        });

        // 普通按钮
        builder.setNeutralButton("普通按钮", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "你点击了普通按钮~", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();  // 创建 AlertDialog 对象
        alert.show();    // 显示对话框
    }
}
```


---

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-3.png" width="280"/>
</div>

---

```java
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] lang = new String[]{"Kotlin", "Java", "Python", "PHP", "C#", "Ruby", "Perl"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.info).setTitle("选择你喜欢的开发语言");

        builder.setItems(lang, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你选择了" + lang[which], Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-4.png" width="280"/>
</div>

---

```java
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] city = new String[]{"北京", "上海", "广州", "深圳", "杭州", "成都", "厦门"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setIcon(R.drawable.info).setTitle("选择你想去的城市，只能选一个哦~");

        builder.setSingleChoiceItems(city, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你选择了" + city[which], Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-5.png" width="280"/>
</div>