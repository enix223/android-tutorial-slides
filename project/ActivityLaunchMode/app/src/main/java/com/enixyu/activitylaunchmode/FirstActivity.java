package com.enixyu.activitylaunchmode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class FirstActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigate);

    TextView label = findViewById(R.id.label);
    label.setText(R.string.activity_first_label);

    Button btn = findViewById(R.id.btn);
    btn.setOnClickListener((v) -> {
      var intent = new Intent(FirstActivity.this, SecondActivity.class);
      startActivity(intent);
    });
  }
}
