# 几种常用的对话框

- 接下来学习如何使用 AlertDialog 创建几种常见的对话框

    1. 普通对话框

    2. 普通列表对话框

    3. 单选列表对话框

---

# 创建AlertDialog
定义标题和内容

```java
// 创建一个AlertDialog Builder
AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 设置图标
builder.setIcon(com.google.android.material.R.drawable.abc_ic_star_black_16dp)

// 设置提示标题
builder.setTitle("提示");

// 设置提示语
builder.setMessage("是否确定提交?");
```
---

# 创建AlertDialog
定义取消和确定按钮

```java
// 定义取消按钮和响应事件
builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(UserProfileActivity.this, "点击了取消按钮", Toast.LENGTH_LONG).show();
    }
});

// 定义确定按钮和响应事件
builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(UserProfileActivity.this, "点击了确定", Toast.LENGTH_LONG).show();
    }
});
```

---

# 创建AlertDialog
基于构造器Builder的配置，创建AlertDialog

```java
// 创建alert dialog
AlertDialog alertDialog = builder.create();

// 显示对话框
alertDialog.show();
```

---

# AlertDialog效果

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-3.png" class="w-[20%]"/> 
</div>

---

# AlertDialog列表选择

```java
public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置选项
        final String[] hobbies = new String[]{"篮球", "足球", "美食", "旅游", "摄影"};
        // 创建构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 设置标题
        builder.setIcon(R.drawable.info).setTitle("选择你爱好");
        // 设置选择事件处理器
        builder.setItems(hobbies, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你选择了" + hobbies[which], Toast.LENGTH_SHORT).show();
            }
        });
        // 显示对话框
        AlertDialog alert = builder.create();
        alert.show();
    }
}
```

---

# AlertDialog列表选择

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-4.png" class="w-[20%]"/>
</div>

---

# AlertDialog单选列表

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

# AlertDialog单选列表效果

<div class="flex flex-col items-center justify-center">
    <img src="/android-alertdialog-5.png" class="w-[20%]"/>
</div>
