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
