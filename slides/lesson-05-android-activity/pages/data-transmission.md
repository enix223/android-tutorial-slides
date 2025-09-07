# 数据传递与回传

- 使用 Intent 在 Activity 之间传递数据

```java
// 发送数据
Intent intent = new Intent(this, SecondActivity.class);
intent.putExtra("KEY_NAME", "John Doe");
intent.putExtra("KEY_AGE", 25);
startActivity(intent);

// 接收数据
String name = getIntent().getStringExtra("KEY_NAME");
int age = getIntent().getIntExtra("KEY_AGE", 0);
```