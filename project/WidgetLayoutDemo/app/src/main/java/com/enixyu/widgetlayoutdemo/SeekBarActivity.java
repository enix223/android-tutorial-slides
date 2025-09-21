package com.enixyu.widgetlayoutdemo;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SeekBarActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seek_bar);

    SeekBar seekBar = findViewById(R.id.seekbar_example);
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) { }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(SeekBarActivity.this, "Progress: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
