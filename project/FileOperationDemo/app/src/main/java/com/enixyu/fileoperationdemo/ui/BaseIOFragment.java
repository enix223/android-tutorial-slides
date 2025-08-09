package com.enixyu.fileoperationdemo.ui;

import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.enixyu.fileoperationdemo.db.DataProvider;
import com.enixyu.fileoperationdemo.db.Todo;
import java.io.IOException;

public abstract class BaseIOFragment extends Fragment {

  protected final DataProvider mDataProvider;

  public BaseIOFragment(DataProvider mDataProvider) {
    this.mDataProvider = mDataProvider;
  }

  @Nullable
  protected Todo read() {
    try {
      return mDataProvider.read();
    } catch (IOException e) {
      Toast.makeText(getActivity(), "读取记录失败", Toast.LENGTH_SHORT).show();
      return null;
    }
  }

  protected void write(@Nullable String title, boolean done) {
    if (title == null || title.isEmpty()) {
      Toast.makeText(getActivity(), "代办事项不能为空", Toast.LENGTH_SHORT).show();
      return;
    }
    var todo = new Todo(title, done);
    try {
      mDataProvider.update(todo);
      Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
    } catch (IOException e) {
      Toast.makeText(getActivity(), "保存记录失败", Toast.LENGTH_SHORT).show();
    }
  }
}
