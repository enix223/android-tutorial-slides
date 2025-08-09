package com.enixyu.fileoperationdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.fileoperationdemo.R;
import com.enixyu.fileoperationdemo.db.DataProvider;

public class FileIoFragment extends BaseIOFragment {

  public FileIoFragment(DataProvider mDataProvider) {
    super(mDataProvider);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_file_io, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    EditText editText = view.findViewById(R.id.et_note);

    // 读取按钮
    Button readBtn = view.findViewById(R.id.btn_read_from_file);
    readBtn.setOnClickListener(view1 -> {
      var todo = read();
      if (todo != null) {
        editText.setText(todo.getTitle());
      }
    });

    // 写入按钮
    Button writeBtn = view.findViewById(R.id.btn_write_to_file);
    writeBtn.setOnClickListener(view1 -> {
      write(editText.getText().toString(), false);
    });
  }
}
