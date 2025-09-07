# Activity 启动模式 - singleTop

- 栈顶复用模式

- 如果目标 Activity 已在栈顶，不会创建新实例

- 防止快速点击时重复创建

<img src="/singleTop.png" width="400" />

```xml
<activity android:name=".MainActivity"
          android:launchMode="singleTop">
</activity>
```