package com.enixyu.mqttcontroller.ui;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

  protected final String TAG = getClass().getSimpleName();
  private final List<Disposable> disposables = new ArrayList<>();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(!isTaskRoot());
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    for (Disposable disposable : disposables) {
      disposable.dispose();
    }
    super.onDestroy();
  }

  protected void addDisposable(Disposable disposable) {
    disposables.add(disposable);
  }
}
