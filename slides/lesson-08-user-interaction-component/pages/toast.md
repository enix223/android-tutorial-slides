# Toast 消息框

消息框可以在一个小型弹出式窗口中提供与操作有关的简单反馈。消息框只会填充消息所需的空间大小，并且当前 activity 会一直显示并供用户与之互动。超时后，消息框会自动消失。

<img src="/toast.png" class="w-[50%]" />

* 轻提示，不会过多的占用屏幕空间
* 无需用户干预，有一段显示时效，超时后自动消失
* 一般用于错误提示，警告等。

---

# Toast的使用

```java
Toast toast = Toast.makeText(this /* MyActivity */, "保存成功", Toast.LENGTH_SHORT);
toast.show();
```

或链式调用消息框方法

```java
Toast.makeText(this /* MyActivity */, "保存成功", Toast.LENGTH_SHORT).show();
```