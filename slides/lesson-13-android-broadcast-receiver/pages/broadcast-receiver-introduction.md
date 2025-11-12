# Broadcast Receiver简介

  broadcast receiver做为四大组件之一，负责组件之间消息的传递和接收。

* 采用观察者模式，基于消息的发布/订阅事件模型，通过Binder机制进行消息的注册和接收。

* 广播中的角色：

  1. 消息订阅者（广播接收者）

  2. 消息发布者（广播发布者）

  3. 消息中心（AMS，即Activity Manager Service）

---

* 广播的工作流程：

  1. 广播接收者通过 Binder机制在AMS注册

  2. 广播发送者通过 Binder 机制向AMS发送广播

  3. AMS根据广播发送者要求，在已注册列表中，寻找合适的广播接收者（寻找依据：IntentFilter / Permission）

  4. AMS将广播发送到合适的广播接收者相应的消息循环队列中；

  5. 广播接收者通过消息循环拿到此广播，并回调 onReceive()

---

# 广播的注册

* 注册类型有两种：静态注册和动态注册

* 静态注册，即在清单文件中注册

* 第一步：创建一个广播接收器BroadcastReceiver ，广播也是通过Intent来传递数据。

```java
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("msg");
        Log.e(TAG, "onReceive: "+msg);
    }
}
```

---

* 第二步，清单文件注册该广播

```xml
<application
	android:allowBackup="true"
	android:icon="@mipmap/ic_launcher"
	android:label="@string/app_name"
	android:supportsRtl="true"
	android:theme="@style/Theme.MyApplication">
 
	<receiver android:name=".receiver.MyReceiver"
		android:exported="true"/>
	
</application>
```

* 第三步：在其它组件里面发送广播，比如Activity里面

```java
private void buttonClick(){
	Intent intent = new Intent(this, MyReceiver.class);
	sendBroadcast(intent);
}
```


* 注意：android 8.0以后版本对静态注册的广播做了限制，自定义的接收器会接收不到发送方发送的广播。发送方需要在intent中设定接收方的package，接收方才会接收到。如下：

```java
Intent intent=new Intent(this, MyOrderBroadcastReceiver.class);
intent.putExtra("msg","你好啊");
sendBroadcast(intent);
```

---

* 动态注册：即通过 registerReceiver 注册

* 第一步：一样先创建广播接收器

```java
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.e(TAG, "onReceive: "+msg);
    }
}
```

* 第二步：在组件如Activity里面动态注册广播

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver myReceiver = new MyReceiver();     //动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.dinghe.test");
        // Android 13 以后 需要多配置一个参数 Context.RECEIVER_NOT_EXPORTED  仅限应用内广播 Context.RECEIVER_EXPORTED 监听系统广播
        registerReceiver(mBroadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
    }
}
```
---

* 第三步：在任意组件里面发送广播

```java
private void buttonClick(){
	Intent intent=new Intent();
	intent.setAction("com.dinghe.test");
	intent.putExtra("msg","你好啊");
    intent.setPackage(getPackageName());
	sendBroadcast(intent);
}
```

* 第四步：取消注册，不然容易造成内存泄漏

```java
@Override
protected void onDestroy() {
	super.onDestroy();
	if (myReceiver!=null){
		unregisterReceiver(myReceiver);
	}
}
```

---

# 有序广播

* 有序广播介绍:

  1. 有序广播是一种同步执行的广播，广播发出之后，优先级高的广播接收器就可以先接收到广播消息。
  
  2. 执行完该广播接收器的逻辑后，可以选择截断正在传递的广播或者继续传递，如果广播消息被截断，之后的广播接收器则无法收到广播消息。
  
  3. 有序广播中的“有序”是针对广播接收者而言的。有序广播的定义过程与普通广播无异，只是其发送方式变为：sendOrderedBroadcast()。
  
  4. 有序广播的接收者们将按照事先命的优先级依次接收，数越大优先级越高（取值范围：-1000~10000）
  
  5. 优先级可以声明在&lt;intent-filter android:priority="100"/&gt;
  
  6. 也可以调用IntentFilter对象的 setPriority() 设置
  
  7. 调用abortBroadcast()方法即可终止，一旦终止后面接收者就无法接受广播