# Activity 启动模式 - standard

- 默认启动模式

- 每次启动都会创建新的实例

- 允许多个相同 Activity 实例存在

<img src="/standard.png" width="400" />

```xml
<activity android:name=".MainActivity"
          android:launchMode="standard">
</activity>
```