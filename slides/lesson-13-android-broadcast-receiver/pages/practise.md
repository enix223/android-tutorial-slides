# 课堂练习
使用BroadcastReceiver，实现自定义广播的发送与接收

1. 创建一个BroadcastReceiver: `CustomBroadcastReceiver`

```java
public class CustomBroadcastReceiver extends BroadcastReceiver {

  private final String TAG = getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "接收到自定义标准通知");
    Toast.makeText(context, "接收到自定义标准广播", Toast.LENGTH_LONG).show();
  }
}
```

---

# 课堂练习（续）

2. 在AndroidManifest.xml中注册该BroadcastReceiver

> action中的包名xxx.yyy必须替换为你自己的app包名!!!

```xml
    <!-- 静态注册自定义标准广播 -->
    <receiver
      android:exported="true"
      android:enabled="true"
      android:name=".CustomBroadcastReceiver">
      <intent-filter>
        <action android:name="com.xxx.yyy.CUSTOM_STANDARD_BROADCAST" />
        <category android:name="android.intent.category.DEFAULT"/>
      </intent-filter>
    </receiver>
```

---

# 课堂练习（续）

3. 创建一个Activity: MainActivity

> Intent中的包名xxx.yyy必须替换为你自己的app包名!!!

```java
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btn = findViewById(R.id.button);
    btn.setOnClickListener((v) -> {
      Intent intent = new Intent("com.xxx.yyy.CUSTOM_STANDARD_BROADCAST");
      intent.setPackage(getPackageName());
      // 发送标准广播
      sendBroadcast(intent);
    });
  }
}
```

---

# 课堂练习（续）

4. 添加页面布局

布局中添加一个按钮，通过该按钮实现手动触发发送广播，需要注意的是按钮的id必须与activity中的一致

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:gravity="center"
  android:orientation="vertical">

  <Button
    android:id="@+id/button"
    android:text="发送标准广播"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
</LinearLayout>
```
