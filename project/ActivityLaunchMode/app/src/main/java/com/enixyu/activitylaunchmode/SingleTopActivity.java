package com.enixyu.activitylaunchmode;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class SingleTopActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_mode);

    TextView label = findViewById(R.id.label);
    label.setText(R.string.activity_single_top_label);
  }
}
