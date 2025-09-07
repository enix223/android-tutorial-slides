# Intent 简介

- Intent 是 Android 中用于组件间通信的消息对象

- 启动 Activity

- 启动 Service

- 发送广播


```java
// 显式Intent
Intent intent = new Intent(this, SecondActivity.class);
startActivity(intent);

// 隐式Intent
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://www.android.com"));
startActivity(intent);
```