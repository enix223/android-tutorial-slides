package com.enixyu.activitylaunchmode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

  private int a = 1;
  private int b = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    Button button = findViewById(R.id.navigate_calculator_btn);
    button.setOnClickListener((v) -> {
      gotoCalculator();
    });
  }

  private void gotoCalculator() {
    var intent = new Intent(MainActivity.this, CalculatorActivity.class);
    intent.putExtra("a", a++);
    intent.putExtra("b", b++);
    startActivityForResult(intent, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1 && resultCode == 2 && data != null) {
      TextView result = findViewById(R.id.result);
      var sum = data.getIntExtra("sum", 0);
      result.setText(String.valueOf(sum));
    }
  }
}