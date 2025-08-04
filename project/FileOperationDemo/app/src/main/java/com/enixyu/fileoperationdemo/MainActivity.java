package com.enixyu.fileoperationdemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.fileoperationdemo.db.DataProvider;
import com.enixyu.fileoperationdemo.db.FileDataProvider;
import com.enixyu.fileoperationdemo.db.SharedPrefDataProvider;
import com.enixyu.fileoperationdemo.db.SqliteDataProvider;

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
  }
}
