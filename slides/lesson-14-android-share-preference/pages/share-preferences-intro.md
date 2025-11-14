
# SharedPreferences概述

  SharedPreferences 是 Android 提供的一种轻量级数据存储方式，用于存储简单的键值对数据。它适合保存应用的配置信息、用户偏好设置等小量数据。

  SharedPreferences 以 XML 文件的形式存储在设备的 `/data/data/<package_name>/shared_prefs/` 目录下，具有以下特点：

*  只能存储基本数据类型：boolean, float, int, long, string 和 string set

* 数据以键值对形式存储

* 线程安全

* 不支持多进程共享（除非使用 MODE_MULTI_PROCESS，但已废弃）

---

# 获取 SharedPreferences 实例

  有三种方式获取 SharedPreferences 对象：

1. Context.getSharedPreferences()

```java
// 指定文件名和模式
SharedPreferences sharedPref = context.getSharedPreferences(
    "my_preferences", Context.MODE_PRIVATE);
```

2. Activity.getPreferences()

```java
// 使用 Activity 类名作为文件名，只适用于当前 Activity
SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
```

3. PreferenceManager.getDefaultSharedPreferences()

```java
// 获取默认的 SharedPreferences 文件 (包名_preferences.xml)
SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
```

---

# 存储模式

* MODE_PRIVATE：默认模式，只有当前应用可以访问

* MODE_WORLD_READABLE 和 MODE_WORLD_WRITEABLE：已在 API 17 废弃

* MODE_MULTI_PROCESS：已在 API 23 废弃

---

# SharedPreferences使用

- 增加数据

```java 
 //获取SharedPreferences对象
  SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
 //获取Editor对象的引用
  SharedPreferences.Editor editor = sharedPreferences.edit();
 //将获取过来的值放入文件
  editor.putString("name", "lucas");
  editor.putInt("age", 30);
  editor.putBoolean("islogin",true);
  // 提交数据
  editor.commit();
```

- editor类核心方法

<div class="flex flex-col items-center justify-center">
    <img src="/android-share-preferences-1.png" width="500"/>
</div>

* 从editor的put方法可以看出SharedPreferences提供了string，set，int，long，float，boolean六种数据类型。

---

# getSharedPreferences方法参数详解

```java 
@Override
public SharedPreferences getSharedPreferences(String name, int mode) {
        
}
```

* name 存储文件名字

* mode 存储方式 其值的含义如下




<div style="display: table; border-collapse: collapse; width: 100%;">

  <!-- 表头 -->
  <div style="display: table-row; background-color: #f0f0f0; font-weight: bold;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_PRIVATE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该SharedPreferences数据只能被本应用程序读、写</div>
  </div>
  

  <!-- 数据行 -->
  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_WORLD_READABLE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该SharedPreferences数据能被其他应用程序读，但不能写</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_WORLD_WRITEABLE</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">指定该SharedPreferences数据能被其他应用程序读</div>
  </div>

  <div style="display: table-row;">
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">Context.MODE_APPEND</div>
    <div style="display: table-cell; border: 1px solid #ccc; padding: 8px;">该模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件；</div>
  </div>

</div>

---

# SharedPreference内部实现
ShardPreference在data/data/应用程序包名的shared_prefs文件夹下生成了一个user.xml的xml文件

```xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="name">lucas</string>
    <int name="age" value="30" />
    <boolean name="islogin" value="true" />
</map>
```


<div class="flex flex-col items-center justify-center">
    <img src="/android-share-preferences-2.gif" width="600"/>
</div>


---

# SharedPreference使用

- **读取数据**, 读取数据可以通过获取SharedPreferences对象，通过SharedPreferences 对象可以获取存储的数据值，第二个参数一般是个默认值，表示当获取数据的时候没有该key则返回一个默认值。

  ```java
  SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);
  String name=sharedPreferences.getString("name","");
  int age = sharedPreferences.getInt("age",0);
  boolean islogin = sharedPreferences.getBoolean("islogin",true);
  Log.i("lucashu","name:"+ name +" age:" + age +" islogin:" + islogin);
  ```

- **删除数据**, 删除数据跟添加数据有点类似 也是通过Editor对象来完成

  ```java
  //获取SharedPreferences对象
  SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
  //获取Editor对象的引用
  SharedPreferences.Editor editor = sharedPreferences.edit();
  //将获取过来的值放入文件
  editor.remove("name");
  // 提交数据
  editor.commit();
  ```

---

# SharedPreference使用

- **修改数据**, 修改数据跟增加数据类似，覆盖原来的数据即修改数据。

  ```java
  //获取SharedPreferences对象
    SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
  //获取Editor对象的引用
    SharedPreferences.Editor editor = sharedPreferences.edit();
  //将获取过来的值放入文件
    editor.putString("name", "lucas1");
    editor.putInt("age", 31);
    editor.putBoolean("islogin",false);
    // 提交数据
    editor.commit();
  ```

- **清除数据**, 我们可以通过Editor对象的clear方法来完成清楚数据

  ```java
  //获取SharedPreferences对象
  SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
  //获取Editor对象的引用
  SharedPreferences.Editor editor = sharedPreferences.edit();
  editor.clear();
  // 提交数据
  editor.commit();
  ```
