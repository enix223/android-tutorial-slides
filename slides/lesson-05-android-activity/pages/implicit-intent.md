# 隐式 Intent

- 不指定具体组件，通过 Action、Category 等匹配

- 启动其他应用的组件

- 更灵活，不需要知道具体类名

```java
// 打开网页
Intent intent = new Intent(Intent.ACTION_VIEW, 
                          Uri.parse("https://www.android.com"));
startActivity(intent);

// 拨打电话
Intent intent = new Intent(Intent.ACTION_DIAL, 
                          Uri.parse("tel:123456"));
startActivity(intent);
```