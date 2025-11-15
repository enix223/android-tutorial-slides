# 课堂作业
使用SharedPreference实现前面作业（学生信息表单）的数据保存与加载显示

在保存代码的最后一行，添加保存动作

```java
...
result.setText(String.format("名字:%s,性别: %s,年龄: %d爱好: %s", name, gender, age, hobbies));

// 增加如下代码保存学生信息
SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
sharedPreferences.edit()
            .putString("name", name)
            .putString("gender", gender)
            .putInt("age", age)
            .putStringSet("hobbies", new HashSet<>(hobbies))
            .apply();
```

---

# 课堂作业
增加读取学生信息代码

在`TextView result = findViewById(R.id.result);`后增加下面的代码

```java
// 从文件读取学生信息
SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

// 名字读取
editTextName.setText(sharedPreferences.getString("name", ""));

// 年龄读取
seekBarAge.setProgress(sharedPreferences.getInt("age", 1));
```

---

# 课堂作业

```java
// 性别读取
String gender = sharedPreferences.getString("gender", "");
if (gender.equals("男")) {
    radioGroupGender.check(R.id.radio_gender_male);
} else {
    radioGroupGender.check(R.id.radio_gender_female);
}

// 兴趣读取
Set<String> hobbies = sharedPreferences.getStringSet("hobbies", new HashSet<>());
for (String hobby : hobbies) {
    if (hobby.equals("运动")) {
    checkboxHobbySport.setChecked(true);
    }
    if (hobby.equals("看书")) {
    checkboxHobbyReading.setChecked(true);
    }
    if (hobby.equals("吃")) {
    checkboxHobbyEating.setChecked(true);
    }
}
```