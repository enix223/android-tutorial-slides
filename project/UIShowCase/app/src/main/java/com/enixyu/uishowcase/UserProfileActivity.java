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
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfileActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_user_profile);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    EditText editTextName = findViewById(R.id.edittext_name);
    RadioGroup radioGroupGender = findViewById(R.id.radio_group_gender);
    SeekBar seekBarAge = findViewById(R.id.seekbar_age);
    CheckBox checkboxHobbySport = findViewById(R.id.checkbox_sport);
    CheckBox checkboxHobbyReading = findViewById(R.id.checkbox_reading);
    CheckBox checkboxHobbyEating = findViewById(R.id.checkbox_eating);
    Button submit = findViewById(R.id.button_submit);

    submit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String name = editTextName.getText().toString();

      }
    });
  }
}