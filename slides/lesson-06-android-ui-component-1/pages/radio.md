# 单选按钮RadioGroup和RadioButton

RadioGroup和RadioButton组合生成的是单选按钮的效果，一个RadioGroup下面可以有多个RadioButton，但只能有一个RadioButton是处于选中状态

* RadioGroup属性

| 属性 | 说明 |
| :--- | :--- |
| **android:orientation** | 用于设置单选按钮组的方向，horizontal: 水平方向; vertical: 垂直方向 |
| **android:checkedButton** | 指定被选中的RadioButton的ID，例如@id/radio_button_1 |

<p />

* RadioButton属性

| 属性 | 说明 |
| :--- | :--- |
| **android:checked** | 设置单选按钮是否被选中 |
| **android:text** | 设置单选项的标签文字 |
| **android:id** | 设置单选按钮的ID，配合RadioGroup的checkedButton属性设置被选中的选项 |

---

# 单选按钮的使用（代码）

```xml
<RadioGroup
    android:id="@+id/radio_group"
    android:orientation="horizontal"
    android:checkedButton="@id/radio_button_2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">
    <RadioButton
        android:id="@+id/radio_button_1"
        android:text="选项一"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <RadioButton
        android:id="@+id/radio_button_2"
        android:text="选项二"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <RadioButton
        android:enabled="false"
        android:buttonTint="@android:color/holo_red_dark"
        android:id="@+id/radio_button_3"
        android:text="选项三"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</RadioGroup>
```

---

# 单选按钮与activity的交互

```java
// 获取radio group实例
RadioGroup radioGroup = findViewById(R.id.radio_group);
// 设置选项3被选中
radioGroup.check(R.id.radio_button_3);
// 注册选择变更事件
radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
    if (i == R.id.radio_button_1) {
        Log.d("Main", "选项一被选中");
    } else if (i == R.id.radio_button_2) {
        Log.d("Main", "选项二被选中");
    } else if (i == R.id.radio_button_3) {
        Log.d("Main", "选项三被选中");
    }
    }
});
```
