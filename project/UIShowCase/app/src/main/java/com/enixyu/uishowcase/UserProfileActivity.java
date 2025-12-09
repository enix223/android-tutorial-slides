package com.enixyu.uishowcase;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    dbHelper = new DBHelper(this);
    sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

    editTextName = findViewById(R.id.edittext_name);
    radioGroupGender = findViewById(R.id.radio_group_gender);
    seekBarAge = findViewById(R.id.seekbar_age);
    checkboxHobbySport = findViewById(R.id.checkbox_sport);
    checkboxHobbyReading = findViewById(R.id.checkbox_reading);
    checkboxHobbyEating = findViewById(R.id.checkbox_eating);
    submit = findViewById(R.id.button_submit);
    result = findViewById(R.id.result);

    loadFromDb();

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

        saveToDb(name, gender, age, hobbies);
        Toast.makeText(UserProfileActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        result.setText(
            String.format(Locale.getDefault(), "名字:%s,性别: %s,年龄: %d爱好: %s", name, gender,
                age, hobbies));
      }
    });
  }

  private void showDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setIcon(R.drawable.info);
    builder.setTitle("提示");
    builder.setMessage("是否确定提交?");
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(UserProfileActivity.this, "点击了取消按钮", Toast.LENGTH_LONG).show();
      }
    });
    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(UserProfileActivity.this, "点击了确定", Toast.LENGTH_LONG).show();
      }
    });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  private void showChoices() {
    // 设置选项
    final String[] hobbies = new String[]{"篮球", "足球", "美食", "旅游", "摄影"};
    // 创建构造器
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // 设置标题
    builder.setIcon(R.drawable.info).setTitle("选择你爱好");
    // 设置选择事件处理器
    builder.setItems(hobbies, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getApplicationContext(), "你选择了" + hobbies[which], Toast.LENGTH_SHORT)
            .show();
      }
    });
    // 显示对话框
    AlertDialog alert = builder.create();
    alert.show();
  }

  private void showSingleChoice() {
    final String[] city = new String[]{"北京", "上海", "广州", "深圳", "杭州", "成都", "厦门"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setIcon(R.drawable.info).setTitle("选择你想去的城市，只能选一个哦~");

    builder.setSingleChoiceItems(city, 0, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getApplicationContext(), "你选择了" + city[which], Toast.LENGTH_SHORT)
            .show();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  private void loadFromSharedPref() {
    // 名字读取
    editTextName.setText(sharedPreferences.getString("name", ""));
    // 年龄读取
    seekBarAge.setProgress(sharedPreferences.getInt("age", 1));
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
  }

  private void saveToSharedPref(String name, String gender, int age, List<String> hobbies) {
    // 保存到文件
    sharedPreferences.edit()
        .putString("name", name)
        .putString("gender", gender)
        .putInt("age", age)
        .putStringSet("hobbies", new HashSet<>(hobbies))
        .apply();
  }

  private void loadFromDb() {
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    try (Cursor cursor = db.rawQuery("SELECT name, gender, age, hobbies FROM student WHERE id = 1",
        null)) {
      // 读取下一行
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
}