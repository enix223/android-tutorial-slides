package com.enixyu.fileoperationdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.fileoperationdemo.R;
import com.enixyu.fileoperationdemo.db.DataProvider;

public class SQLiteFragment extends BaseIOFragment {

  public SQLiteFragment(DataProvider mDataProvider) {
    super(mDataProvider);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_sqlite, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    EditText editText = view.findViewById(R.id.et_note);

    // 读取按钮
    Button readBtn = view.findViewById(R.id.btn_read_from_sqlite);
    readBtn.setOnClickListener(view1 -> {
      var todo = read();
      if (todo != null) {
        editText.setText(todo.getTitle());
      }
    });

    // 写入按钮
    Button writeBtn = view.findViewById(R.id.btn_write_to_sqlite);
    writeBtn.setOnClickListener(view1 -> write(editText.getText().toString(), false));
  }
}
