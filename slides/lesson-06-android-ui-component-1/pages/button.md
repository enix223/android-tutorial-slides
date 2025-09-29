# Button
一个提供给用户点击的控件。是继承自 TextView，拥有 TextView 的全部属性。

| 属性 | 作用 | 说明 |
|-|-|-|
| android:onClick | 点击事件 | 设置按钮的点击事件 |
| android:enabled | 是否启用 | 若设置为false，按钮的会显示禁用状态的颜色 |
---

# Button - 静态绑定

- 基本用法和 TextView 类似，这里多了一个android:onClick属性，使用静态绑定需要设置onClick

    ```xml 
    <Button
        android:id="@+id/btn_click"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="点我"
        android:onClick="onClick" />
    ```

- 静态绑定，我们需要给onClick属性设置一个函数“onClick”（函数名任意），用于接收点击事件，所以我们要在 Activity 里面添加一个名为onClick的方法，如下：

    ```java
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
    }
    ```

---

# Button - 事件的动态绑定

动态绑定，在代码中绑定一个事件监听器

布局文件

```xml 
<Button
    android:id="@+id/btn_click"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="点我" />
```

Activity中

```java 
Button button = findViewById(R.id.btn_click);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
    }
});
```
