# 数据回传

- 使用 startActivityForResult 获取返回数据

```java
// 启动Activity并期待返回结果
Intent intent = new Intent(this, SecondActivity.class);
startActivityForResult(intent, REQUEST_CODE);

// 在目标Activity中设置返回数据
Intent resultIntent = new Intent();
resultIntent.putExtra("RESULT_KEY", "Some result");
setResult(RESULT_OK, resultIntent);
finish();
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 
练习：

1. 创建两个Activity：MainActivity和UserDetailActivity
2. 在MainActivity中使用显式Intent传递用户数据
3. 在UserDetailActivity中接收并打印数据 
</div>

---

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="center">

    <Button
        android:id="@+id/btn_send_data"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="发送用户数据" />

</LinearLayout>
```

---

```java
public class MainActivity extends AppCompatActivity {
    private EditText etUserId, etUserName, etUserAge;
    private Button btnSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendData = findViewById(R.id.btn_send_data); // 初始化UI组件

        btnSendData.setOnClickListener(new View.OnClickListener() {   // 设置按钮点击事件
            @Override
            public void onClick(View v) {
                // 创建显式Intent
                Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                
                // 添加额外数据
                intent.putExtra("user_id", 1);
                intent.putExtra("user_name", "张三");
                intent.putExtra("user_age", 24);
                
                // 启动Activity
                startActivity(intent);
            }
        });
    }
}

```
---

```java
public class UserDetailActivity extends AppCompatActivity {

    private static final String TAG = "UserDetailActivity";
    
    private TextView tvUserId, tvUserName, tvUserAge, tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // 获取Intent
        Intent intent = getIntent();
        
        // 提取数据
        int userId = intent.getIntExtra("user_id", 0);
        String userName = intent.getStringExtra("user_name");
        int userAge = intent.getIntExtra("user_age", 0);
        
        // 同时输出到Logcat
        Log.d(TAG, "收到用户数据: userId=" + userId + 
                   ", userName=" + userName + ", userAge=" + userAge);
    }
}
```