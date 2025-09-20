
## Button

- 顾名思义，就是一个提供给用户点击的控件。是继承自 TextView，拥有 TextView 的全部属性。

- 基本用法和 TextView 类似，这里多了一个android:onClick属性，使用静态绑定需要设置onClick

```xml 
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <Button
        android:id="@+id/btn_click"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="点我"
        android:onClick="onClick" />
        
</LinearLayout>
```

- 静态绑定，我们需要给onClick属性设置一个函数“onClick”（函数名任意），用于接收点击事件，所以我们要在 Activity 里面添加一个名为onClick的方法，如下：

```java
public void onClick(View v) {
    Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
}
```
---

## 动态绑定

- 通过xml的onClick属性静态绑定的方式不够灵活，而且要求绑定的函数名、参数等完全一致，出错率也比较高，所以在实际开发中用的很少，绝大多数场景都会通过 Java 代码动态绑定一个事件监听器。
动态绑定监听器的主要代码如下：

```java 
public class MainActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ···
        Button button = findViewById(R.id.btn_click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

---

<div class="flex flex-col items-center justify-center">
    <img src="/button.png" width="200"/>
</div>

<div v-click style="margin-top: 15px; border-left: 5px solid #3498db; background: #f0f8ff; padding: 10px 15px; border-radius: 4px; display: inline-block;width: 800px;"> 练习：通过点击事件来更改TextView的文字、字体和颜色</div>