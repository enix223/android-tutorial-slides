package com.enixyu.fileoperationdemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.fileoperationdemo.db.FileDataProvider;
import com.enixyu.fileoperationdemo.db.SharedPrefDataProvider;
import com.enixyu.fileoperationdemo.db.SqliteDataProvider;
import com.enixyu.fileoperationdemo.ui.FileIoFragment;
import com.enixyu.fileoperationdemo.ui.SQLiteFragment;
import com.enixyu.fileoperationdemo.ui.SharedPrefFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

  private SqliteDataProvider mSqliteDataProvider;
  private SharedPrefDataProvider mSharedPrefDataProvider;
  private FileDataProvider mFileDataProvider;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSqliteDataProvider = new SqliteDataProvider(this);
    mSharedPrefDataProvider = new SharedPrefDataProvider(this);
    mFileDataProvider = new FileDataProvider(this);

    MaterialButtonToggleGroup btnGroup = findViewById(R.id.btn_tabs);
    btnGroup.addOnButtonCheckedListener((group, i, checked) -> {
      if (!checked) {
        return;
      }
      var fragmentManager = getSupportFragmentManager();
      if (i == R.id.btn_fileio) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_content, new FileIoFragment(mFileDataProvider))
            .addToBackStack(null)
            .commit();
      } else if (i == R.id.btn_sharedpref) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_content, new SharedPrefFragment(mSharedPrefDataProvider))
            .addToBackStack(null)
            .commit();
      } else if (i == R.id.btn_sqlite) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_content, new SQLiteFragment(mSqliteDataProvider))
            .addToBackStack(null)
            .commit();
      }
    });
    btnGroup.check(R.id.btn_fileio);
  }
}
