package com.enixyu.uishowcase;

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
        result.setText(String.format("名字:%s,性别: %s,年龄: %d爱好: %s", name, gender, age, hobbies));
      }
    });
  }
}