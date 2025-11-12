# 示例

* 第一步，创建有序广播接收器

```java
public class MyOrderBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyOrderBroadcastReceive";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        String msg=intent.getStringExtra("msg");
 
        switch (action){
            case "BROADCAST_ACTION1":
                
                break;
            case "BROADCAST_ACTION2":
                
                break;
        }
    }
}
```

---

* 第二步：清单文件里面注册有序广播 ，并设置优先级

```xml
<receiver 
    android:name=".MyOrderBroadcastReceiver"
    android:exported="true" >
 
    <!-- priority优先级：数字越高优先级越高 -->
    <intent-filter android:priority="2">
        <action android:name="BROADCAST_ACTION2" />
    </intent-filter>
 
    <intent-filter android:priority="1">
        <action android:name="BROADCAST_ACTION1" />
    </intent-filter>
</receiver>
```

* 或者动态注册并设置广播优先级 

```java
MyOrderBroadcastReceiver mBroadcastReceiver = new MyOrderBroadcastReceiver();
IntentFilter intentFilter = new IntentFilter();
intentFilter.addAction("BROADCAST_ACTION2");
intentFilter.setPriority(2);
// Android 13 以后 需要多配置一个参数 Context.RECEIVER_NOT_EXPORTED  仅限应用内广播 Context.RECEIVER_EXPORTED 监听系统广播
registerReceiver(mBroadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
```

---

* 第三步：发送广播

```java
Intent intent = new Intent();
intent.setAction("BROADCAST_ACTION2");
intent.setPackage(getPackageName());
intent.putExtra("msg","你好啊");
sendOrderedBroadcast(intent, null);
```

* 第四步：拦截有序广播，在接收器里面拦截abortBroadcast();

```java
public class MyOrderBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyOrderBroadcastReceive";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
 
        switch (action){
            case "BROADCAST_ACTION1":
                Log.e(TAG, msg);
                break;
            case "BROADCAST_ACTION2":
                //优先级高，先收到消息，可以拦截断开有序广播，不再执行下一广播
                abortBroadcast();
                Log.e(TAG, msg);
                break;
        }
    }
}
```


---

# 应用内广播

* Android中的广播可以跨进程甚至跨App直接通信，且注册是exported对于有intent-filter的情况下默认值是true，由此将可能出现安全隐患如下：

    1. 其他App可能会针对性的发出与当前App intent-filter相匹配的广播，由此导致当前App不断接收到广播并处理；
    
    2. 其他App可以注册与当前App一致的intent-filter用于接收广播，获取广播具体信息。

* 解决款进程上面隐患方案如下：

    1. 对于同一App内部发送和接收广播，将exported属性人为设置成false，使得非本App内部发出的此广播不被接收；
    
    2. 在广播发送和接收时，都增加上相应的permission，用于权限验证；
    
    3. 发送广播时，指定特定广播接收器所在的包名，具体是通过intent.setPackage(packageName)指定在，这样此广播将只会发送到此包中的App内与之相匹配的有效广播接收器中。

---

* LocalBroadcastManage处理App内广播

  Android v4兼容包中给出了封装好的LocalBroadcastManager类，用于统一处理App应用内的广播问题，使用方式上与通常的全局广播几乎相同，只是注册/取消注册广播接收器和发送广播时将主调context变成了LocalBroadcastManager的单一实例。使用方式如下：

```java

//注册应用内广播接收器
LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
 
//unregisterReceiver(mBroadcastReceiver);
//取消注册应用内广播接收器
localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
 
Intent intent = new Intent();
intent.setAction(BROADCAST_ACTION);
//sendBroadcast(intent);
//发送应用内广播
localBroadcastManager.sendBroadcast(intent);
```

---

# 常用系统广播

- Android中内置了多个系统广播：只要涉及到手机的基本操作（如开机、网络状态变化、拍照等等），都会发出相应的广播。

- 当使用系统广播时，只需要在注册广播接收者时定义相关的action即可，并不需要手动发送广播，当系统有相关操作时会自动进行系统广播

- 每个广播都有特定的Intent - Filter（包括具体的action），Android系统广播action如下：

<div style="display: table; border-collapse: collapse; width: 100%;">
  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">事件描述</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">对应的 Intent 常量</div>
  </div>

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">监听网络变化</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">android.net.conn.CONNECTIVITY_CHANGE</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">关闭或打开飞行模式</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_AIRPLANE_MODE_CHANGED</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">充电时或电量发生变化</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_BATTERY_CHANGED</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">电池电量低</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_BATTERY_LOW</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">电池电量恢复正常</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_BATTERY_OKAY</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">系统启动完成后（仅广播一次）</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_BOOT_COMPLETED</div>
  </div>

</div>


---


<div style="display: table; border-collapse: collapse; width: 100%;">

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">按下照相时的拍照按键（硬件按键）</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_CAMERA_BUTTON</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">屏幕锁屏</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_CLOSE_SYSTEM_DIALOGS</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">设备当前设置被改变（语言、方向等）</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_CONFIGURATION_CHANGED</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">插入耳机</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_HEADSET_PLUG</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">未正确移除SD卡但已取出</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_MEDIA_BAD_REMOVAL</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">插入外部存储装置（如SD卡）</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_MEDIA_CHECKING</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">成功安装APK</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_PACKAGE_ADDED</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">成功删除APK</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_PACKAGE_REMOVED</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">重启设备</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_REBOOT</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">屏幕被关闭</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_SCREEN_OFF</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">屏幕被打开</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_SCREEN_ON</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">关闭系统</div>
    <div style="display: table-cell; padding: 8px; border: 1px solid #ccc;">Intent.ACTION_SHUTDOWN</div>
  </div>
</div>

---


# 示例

- 监听事件分钟变化

-  第一步：创建时间变化监听器

```java
BroadcastReceiver mTimeUpdateReceiver = new BroadcastReceiver() {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent == null) {
			return;
		}
		String action = intent.getAction();
		if (action == null || action.isEmpty()) {
			return;
		}
 
		if (Intent.ACTION_TIME_TICK.equals(action)) {
			//system every 1 min send broadcast
			
			Log.e(TAG, "onReceive: ACTION_TIME_TICK");
		}
	}
};
```

---

- 第二步：注册系统监听

```java
/**
 * 注册时间监听
 */
private void registerUpdateTimeReceiver() {
	//register time update
	IntentFilter intentFilter = new IntentFilter();
	intentFilter.addAction(Intent.ACTION_TIME_TICK);
  registerReceiver(mBroadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
}
```

-  第三步：取消系统时间监听

```java
private void unRegisterUpdateTimeReceiver() {
	if (mTimeUpdateReceiver != null) {
		unregisterReceiver(mTimeUpdateReceiver);
	}
}
```

---

# 广播的其它方式

-  广播的作用就是会主动监听并接收数据变化，但广播是系统组件，需要传上下文，使用不规范可能会造成内存泄漏。而且系统的更新可能会限制广播的某些功能使用，于是也出现了基于发布/订阅模式的其它框架，比如使用非常广泛的EventBus。

- EventBus 优点：

    1. 调度灵活，不依赖于 Context，使用时无需像广播一样关注 Context 的注入与传递。
    
    2. 父类对于通知的监听和处理可以继承给子类，这对于简化代码至关重要。
    
    3. 通知的优先级，能够保证 Subscriber 关注最重要的通知。
    
    4. 粘滞事件 (sticky events) 能够保证通知不会因 Subscriber 的不在场而忽略
