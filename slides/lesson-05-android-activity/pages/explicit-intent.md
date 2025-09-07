# 显式 Intent

- 明确指定要启动的组件类名

- 同一应用内跳转

- 需要知道目标组件的完整类名

```java
// 启动同一应用内的Activity
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
startActivity(intent);

// 启动其他应用的Activity（需要知道包名和类名）
Intent intent = new Intent();
intent.setClassName("com.example.otherapp", 
                   "com.example.otherapp.MainActivity");
startActivity(intent);
```

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：创建不同启动模式的Activity并测试行为差异 </div>
