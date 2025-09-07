# Intent 过滤器

- 在 AndroidManifest.xml 中声明组件能处理的 Intent

```xml
<activity android:name=".MyBrowserActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="http" />
        <data android:scheme="https" />
    </intent-filter>
</activity>
```