package com.enixyu.widgetlayoutdemo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_view);

    TextView tv = findViewById(R.id.textview_example);
    tv.setOnClickListener(v -> tv.setText("TextView clicked!"));
  }
}
