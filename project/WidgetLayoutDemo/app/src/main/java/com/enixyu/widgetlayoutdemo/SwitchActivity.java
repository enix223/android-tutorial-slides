package com.enixyu.widgetlayoutdemo;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SwitchActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_switch);

   Switch sw = findViewById(R.id.switch_example);
    sw.setOnCheckedChangeListener((buttonView, isChecked) ->
        Toast.makeText(this, "Switch is " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show()
    );
  }
}
