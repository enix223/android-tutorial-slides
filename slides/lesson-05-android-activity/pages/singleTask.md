# Activity 启动模式 - singleTask

- 栈内复用模式

- 系统中只存在一个实例

- 会清除该 Activity 之上的所有 Activity

<img src="/singleTask.png" width="400" />

```xml
<activity android:name=".MainActivity"
          android:launchMode="singleTask">
</activity>
```