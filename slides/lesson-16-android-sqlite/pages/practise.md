# 课堂练习
修改学生信息表单的页面，使用数据库保存学生信息记录

1. 添加数据库操作类: `DBHelper.java`
2. DBHelper中实现`onCreate`方法，并创建学生表
3. UserProfileActivity增加从数据库读取记录的代码
4. UserProfileActivity增加保存学生信息到数据库的代码

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
DBHelper.java

```java
public class DBHelper extends SQLiteOpenHelper {

  public DBHelper(Context context) {
    super(context, "student.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(
        "CREATE TABLE student ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name TEXT, "
            + "gender TEXT, "
            + "age INTEGER, "
            + "hobbies TEXT"
            + ")"
    );
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  }
}
```

---

# 课堂练习
UserProfileActivity增加读取数据库代码

```java
private void loadFromDb() {
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    try (Cursor cursor = db.rawQuery("SELECT name, gender, age, hobbies FROM student WHERE id = 1", null)) {
      // 判断是否有记录
      if (!cursor.moveToNext()) {
        return;
      }

      // 读取姓名
      editTextName.setText(cursor.getString(0));

      // 读取性别
      String gender = cursor.getString(1);
      if (gender.equals("男")) {
        radioGroupGender.check(R.id.radio_gender_male);
      } else {
        radioGroupGender.check(R.id.radio_gender_female);
      }

      // 读取年龄
      int age = cursor.getInt(2);
      seekBarAge.setProgress(age);
```

---

# 课堂练习
UserProfileActivity增加读取数据库代码

```java
      // 兴趣读取
      String[] hobbies = cursor.getString(3).split(",");
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
    }
  }
```

---

# 课堂练习
UserProfileActivity增加保存数据库代码

```java
private void saveToDb(String name, String gender, int age, List<String> hobbies) {
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    try (var cursor = db.rawQuery("SELECT * FROM student", null)) {
      if (cursor.moveToNext()) {
        // 记录已存在，执行修改
        db.execSQL("UPDATE student SET name = ?, gender = ?, age = ?, hobbies = ? WHERE id = ?",
            new Object[]{name, gender, age, String.join(",", hobbies), 1});
      } else {
        // 记录不存在，执行添加
        db.execSQL("INSERT INTO student (id, name, gender, age, hobbies) VALUES (?, ?, ?, ?, ?)",
            new Object[]{1, name, gender, age, String.join(",", hobbies)});
      }
    }
}
```

---

# 课堂练习
UserProfileActivity改造

```java
public class UserProfileActivity extends AppCompatActivity {
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private SeekBar seekBarAge;
    private CheckBox checkboxHobbySport;
    private CheckBox checkboxHobbyReading;
    private CheckBox checkboxHobbyEating;
    private Button submit;
    private TextView result;
    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        ...
        dbHelper = new DBHelper(this);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        ...
    }
}
```
