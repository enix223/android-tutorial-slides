package com.enixyu.activitylaunchmode;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_calculator);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });
    init();
  }

  private void init() {
    var intent = getIntent();
    var a = intent.getIntExtra("a", 0);
    var b = intent.getIntExtra("b", 0);
    var sum = a + b;
    TextView resultTextView = findViewById(R.id.result);
    resultTextView.setText(String.valueOf(sum));

    var button = findViewById(R.id.add_btn);
    button.setOnClickListener((v) -> {
      var backIntent = new Intent();
      backIntent.putExtra("sum", sum);
      setResult(2, backIntent);
      finish();
    });
  }
}