# 课堂练习

练习使用LinearLayout

* 使用上一堂课的项目或新建一个项目。
* 通过Android Studio创建一个`New -> Activity -> Empty Views Activity`创建一个Activity，并命名为<span class="text-blue-700">LinearLayoutActivity</span>
* 在LinearLayout中添加学生信息记录的表单
* 注册一个按钮事件，点击按钮后，把用户填写的内容显示到结果TextView中

<div class="text-2xl mt-5">课堂PPT</div>
<div class="mt-3">
    <QRCode
        value="http://course.cloudesk.top"
        :width="180"
        :height="180"
        color=""
        image=""
    />
</div>

---

# 课堂练习

效果展示

<div class="flex flex-row">
    <div class="flex flex-1">
        <img src="/homework-1.png" class="w-[25%]"/>
    </div>
</div>

---

# 课堂练习
界面代码实现部分

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:orientation="vertical"
  xmlns:tools="http://schemas.android.com/tools"
  android:paddingHorizontal="15dp"
  android:id="@+id/main"
  android:gravity="bottom"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".UserProfileActivity">

  <!-- 名字 -->
  <TextView
    android:text="名字"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
  <EditText
    android:id="@+id/edittext_name"
    android:layout_marginTop="10dp"
    android:background="@color/grey"
    android:padding="10dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

---

# 课堂练习
界面代码实现部分 (续上)

```xml
  <!-- gender -->
  <TextView
    android:text="性别"
    android:layout_marginTop="20dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
  <RadioGroup
    android:id="@+id/radio_group_gender"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <RadioButton
      android:id="@+id/radio_gender_male"
      android:text="男"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />
    <RadioButton
      android:text="女"
      android:id="@+id/radio_gender_female"
      android:layout_marginStart="10dp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />
  </RadioGroup>
```

---

# 课堂练习
界面代码实现部分 (续上)

```xml
  <!-- 年龄 -->
  <TextView
    android:text="年龄"
    android:layout_marginTop="20dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
  <SeekBar
    android:min="1"
    android:max="100"
    android:progress="18"
    android:progressTint="@color/purple_500"
    android:thumbTint="@color/purple_700"
    android:id="@+id/seekbar_age"
    android:layout_width="match_parent"
    android:layout_height="40dp" />
```

---

# 课堂练习
界面代码实现部分 (续上)

```xml
  <!-- 爱好 -->
  <TextView
    android:text="爱好"
    android:layout_marginTop="20dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
  <LinearLayout
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <CheckBox
      android:text="运动"
      android:id="@+id/checkbox_sport"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />
    <CheckBox
      android:text="看书"
      android:id="@+id/checkbox_reading"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />
```

---

# 课堂练习
界面代码实现部分 (续上)

```xml
    <CheckBox
      android:id="@+id/checkbox_eating"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="美食" />
  </LinearLayout>
  <Button
    android:text="提交"
    android:layout_marginTop="20dp"
    android:id="@+id/button_submit"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

  <TextView
    android:id="@+id/result"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
</LinearLayout>
```

---

# 课堂练习 - Activity代码实现部分

```java
EditText editTextName = findViewById(R.id.edittext_name);
RadioGroup radioGroupGender = findViewById(R.id.radio_group_gender);
SeekBar seekBarAge = findViewById(R.id.seekbar_age);
CheckBox checkboxHobbySport = findViewById(R.id.checkbox_sport);
CheckBox checkboxHobbyReading = findViewById(R.id.checkbox_reading);
CheckBox checkboxHobbyEating = findViewById(R.id.checkbox_eating);
Button submit = findViewById(R.id.button_submit);
TextView result = findViewById(R.id.result);
```

---

# 课堂练习 - Activity代码实现部分

```java
submit.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
    String name = editTextName.getText().toString();
    String gender;
    if (radioGroupGender.getCheckedRadioButtonId() == R.id.radio_gender_female) {
        gender = "女";
    } else {
        gender = "男";
    }
    int age = seekBarAge.getProgress();
    List<String> hobbies = new ArrayList<>();
    if (checkboxHobbySport.isChecked()) {
        hobbies.add("运动");
    }
    if (checkboxHobbyReading.isChecked()) {
        hobbies.add("看书");
    }
    if (checkboxHobbyEating.isChecked()) {
        hobbies.add("吃");
    }
    result.setText(String.format("名字:%s,性别: %s,年龄: %d爱好: %s", name, gender, age, hobbies));
    }
});
```
