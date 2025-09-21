package com.enixyu.widgetlayoutdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setLogo(R.mipmap.ic_launcher); // 设置Logo
    toolbar.setTitle("主标题");   //  主标题
    toolbar.setSubtitle("副标题");  //  副标题
    toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);  // 设置导航图标

    findViewById(R.id.btn_button).setOnClickListener(v ->
        startActivity(new Intent(this, ButtonActivity.class)));

    findViewById(R.id.btn_textview).setOnClickListener(v ->
        startActivity(new Intent(this, TextViewActivity.class)));

    findViewById(R.id.btn_edittext).setOnClickListener(v ->
        startActivity(new Intent(this, EditTextActivity.class)));

    findViewById(R.id.btn_imageview).setOnClickListener(v ->
        startActivity(new Intent(this, ImageViewActivity.class)));

    findViewById(R.id.btn_seekbar).setOnClickListener(v ->
        startActivity(new Intent(this, SeekBarActivity.class)));

    findViewById(R.id.btn_switch).setOnClickListener(v ->
        startActivity(new Intent(this, SwitchActivity.class)));
  }
}
