# 创建测试环境 (1)

<img src="/as-create-project-1.png" class="h-[80%]" />

创建新的项目，并选择`No Activity`

---

# 创建测试环境 (2)

<img src="/as-create-project-2.png" class="h-[70%]" />

* 项目名称 (Project): JavaDemo
* 编程语言 (Language): Java
* 构造配置脚本 (Build Configuration language): Groovy

---

# 创建测试环境 (3)

<img src="/as-create-project-3.png" class="h-[70%]" />

* 右击包名
* 添加一个新的Java类，起名为: `MainActivity`

---

# 创建测试环境 (4)

Activity类填写如下内容: 

```java
package com.enixyu.javademo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void main() {
    // 测试的内容
  }
}
```

---

# 创建测试环境 (5)

AndroidManifest填写如下内容: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
  <application
    android:allowBackup="true"
    android:dataExtractionRules="@xml/data_extraction_rules"
    android:fullBackupContent="@xml/backup_rules"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/Theme.JavaDemo">
    <activity android:exported="true" android:name=".MainActivity">
      <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
    </activity>
  </application>
</manifest>
```
