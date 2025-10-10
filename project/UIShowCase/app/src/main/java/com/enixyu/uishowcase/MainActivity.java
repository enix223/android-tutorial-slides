package com.enixyu.uishowcase;

import android.os.Bundle;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    ///
    /// checkbox
    ///

    // 获取checkbox实例
    CheckBox checkBox1 = findViewById(R.id.checkbox_1);
    // 设置checkbox为选择状态
    checkBox1.setChecked(true);
    // 设置checkbox为选择状态
    checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
        Log.d("Main", b ? "选项一选中" : "选项一取消选中");
      }
    });

    ///
    /// 单选按钮
    ///

    // 获取radio group实例
    RadioGroup radioGroup = findViewById(R.id.radio_group);
    // 设置选项3被选中
    radioGroup.check(R.id.radio_button_3);
    // 注册选择变更事件
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
        if (i == R.id.radio_button_1) {
          Log.d("Main", "选项一被选中");
        } else if (i == R.id.radio_button_2) {
          Log.d("Main", "选项二被选中");
        } else if (i == R.id.radio_button_3) {
          Log.d("Main", "选项三被选中");
        }
      }
    });
  }
}