package com.enixyu.uishowcase;

import android.content.DialogInterface;
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
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    EditText editTextName = findViewById(R.id.edittext_name);
    RadioGroup radioGroupGender = findViewById(R.id.radio_group_gender);
    SeekBar seekBarAge = findViewById(R.id.seekbar_age);
    CheckBox checkboxHobbySport = findViewById(R.id.checkbox_sport);
    CheckBox checkboxHobbyReading = findViewById(R.id.checkbox_reading);
    CheckBox checkboxHobbyEating = findViewById(R.id.checkbox_eating);
    Button submit = findViewById(R.id.button_submit);
    TextView result = findViewById(R.id.result);

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
        Toast.makeText(UserProfileActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        result.setText(String.format("名字:%s,性别: %s,年龄: %d爱好: %s", name, gender, age, hobbies));
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
        Toast.makeText(getApplicationContext(), "你选择了" + hobbies[which], Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getApplicationContext(), "你选择了" + city[which], Toast.LENGTH_SHORT).show();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }
}