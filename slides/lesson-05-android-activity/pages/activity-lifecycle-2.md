# Activity 生命周期 - 第二部分


- onStop() - Activity 完全不可见时调用

- onDestroy() - Activity 销毁时调用

- onRestart() - 从停止状态重新启动时调用


```java
@Override
protected void onPause() {
    super.onPause();
    // 释放资源，保存数据
}

@Override
protected void onDestroy() {
    super.onDestroy();
    // 清理资源，避免内存泄漏
}
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;">
练习：在MainActivity中实现完整的生命周期日志记录
</div>

--- 


```java
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifecycleDemo";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity正在被创建");
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Activity即将可见");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Activity获得焦点，可与用户交互");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Activity失去焦点，但仍部分可见");
    }
```

---     

```java
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Activity完全不可见");
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: Activity从停止状态重新启动");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity正在被销毁");
    }
}
```