# Activity 生命周期 - 第一部分


- onCreate() - Activity 创建时调用

- onStart() - Activity 可见时调用

- onResume() - Activity 获取焦点时调用

- onPause() - Activity 失去焦点时调用


```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 初始化Activity
}

@Override
protected void onStart() {
    super.onStart();
    // Activity即将可见
}
```