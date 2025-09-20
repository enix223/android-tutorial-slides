package com.enixyu.widgetlayoutdemo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditTextActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_text);

    EditText et = findViewById(R.id.edittext_example);
    Button btn = findViewById(R.id.btn_show_text);

    btn.setOnClickListener(v ->
        Toast.makeText(this, "You typed: " + et.getText().toString(), Toast.LENGTH_SHORT).show()
    );
  }
}
