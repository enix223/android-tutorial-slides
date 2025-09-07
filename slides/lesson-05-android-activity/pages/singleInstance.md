# Activity 启动模式 - singleInstance

- 单实例模式

- 单独存在于一个任务栈中

- 保证系统中只有一个实例

<img src="/singleInstance.png" width="400" />

```xml
<activity android:name=".MainActivity"
          android:launchMode="singleInstance">
</activity>
```