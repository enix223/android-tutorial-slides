# Checkbox

checkbox是表示复选框，用户可以选择实现多个选项的选择

| 属性 | 说明 |
| :--- | :--- |
| **android:checked** | 用于设置或判断多选框是否被选中 |
| **android:enabled** | 用于设置多选框是否允许被修改 |
| **android:text** | 在控件后面显示一个文字标签，一般用于描述选项用途 |
| **android:textColor** | 设置标签文字的颜色 |
| **android:buttonTint** | 用于自定义设置switch的圆形按钮的图片 |

---

# Checkbox的代码实现

```xml
<CheckBox
    android:checked="true"
    android:text="选项1"
    android:enabled="false"
    android:buttonTint="@color/black"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

---

# Checkbox与activity的交互

```java
// 获取checkbox实例
CheckBox checkBox1 = findViewById(R.id.checkbox_1);

// 设置checkbox为选择状态
checkBox1.setChecked(true);

// 设置checkbox为选择状态
checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
        Log.d("Main", b ? "选项一选中" : "选项一取消选中");
    }
});
```
