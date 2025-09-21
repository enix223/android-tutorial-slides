
## Toolbar

- 当我们新建一个工程时，我们发现默认是有导航栏的， 如图：

## 效果如图所示：
<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-1.png" width="500"/>
</div>

---

- 那是因为默认app主题自带了导航栏
- 我们发现，系统自带的是一个xxxActionBar，那么我们应该禁用掉 ActionBar

<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-2.png"/>
</div>

---

- 注意 要选择 xxxNoActionBar 也就是无导航栏的样式
- 以上主题任意选择一个即可。

<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-3.png"/>
</div>

---

## Toolbar常用属性

<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-5.png"  width="800"/>
</div>

---

## 现在开始添加一个ToolBar

- 布局中添加Toolbar

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher); // 设置Logo
        toolbar.setTitle("主标题");   //  主标题
        toolbar.setSubtitle("副标题");  //  副标题
        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);  // 设置导航图标
    }
}
```
---

<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-4.png"/>
</div>

---

## 添加菜单

- 先在项目的 res/menu/ 目录中创建新的 XML 文件。

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/action_edit"
        android:icon="@drawable/ab_edit"
        android:orderInCategory="80"
        android:title="@string/action_edit"
        app:showAsAction="ifRoom|withText" />
    <item
        android:id="@+id/action_share"
        android:icon="@drawable/regular_share"
        android:orderInCategory="80"
        android:title="@string/action_share"
        app:showAsAction="ifRoom|withText" />
    <item
        android:id="@+id/action_new"
        android:icon="@drawable/ab_new"
        android:orderInCategory="80"
        android:title="@string/action_new"
        app:showAsAction="ifRoom|withText" />
```

---

```xml
    <item
        android:id="@+id/action_settings"
        android:icon="@drawable/setting"
        android:orderInCategory="80"
        android:title="@string/action_setting"
        app:showAsAction="ifRoom|withText" />
</menu>

```

- 上面代码中组件的添加和Menu绘制的方法类似，唯一的区别就是app:showAsAction值的不同，而该属性正是 Toolbar关键所在。 app:showAsAction 属性共有 4个值，分别如下。
  1）always：这个值会使菜单项一直显示在 ToolBar上。
  2）ifRoom：如果有足够的空间，这个值会使菜单项显示在 Tool Bar上。
  3）never：这个值会使菜单项永远都不出现在 ToolBar上。
  4）withText：这个值会使菜单项和它的图标、菜单文本一起显示。一般和ifRoom一起通过“|”使用

- app:showAsAction 属性值为 ifRoom|withText，表示如果有空间，那么就连同文字一起显示在标题栏中，否则就显示在菜单栏中。
而当app:showAsAction 属性值为 never时，该项作用为Menu不显示在菜单组件中。


```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        Toolbar toolbar = findViewById(R.id.toolbar);
```
---

```java
        toolbar.setLogo(R.mipmap.ic_launcher); //设置Logo
        toolbar.setTitle("主标题"); //主标题
        toolbar.setSubtitle("副标题"); //副标题
        toolbar.setNavigationIcon(R.drawable.ab_android); //设置导航图标
        toolbar.inflateMenu(R.menu.menu_main); //添加菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { //监听菜单项
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {
                    case R.id.action_edit:
                        msg += "点击标签";
                        break;
                    case R.id.action_share:
                        msg += "点击分享";
                        break;
                    case R.id.action_new:
                        msg += "点击新建";
                        break;
                    case R.id.action_settings:
                        msg += "点击退出";
                        break;
                }
                if (!msg.equals("")) {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/toolbar-6.png"/>
</div>
