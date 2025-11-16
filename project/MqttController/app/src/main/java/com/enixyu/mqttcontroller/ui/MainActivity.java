package com.enixyu.mqttcontroller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.adapter.LightArrayAdapter;
import com.enixyu.mqttcontroller.viewmodel.HomeViewModel;

public class MainActivity extends BaseActivity {

  private MenuItem mConnectMenuItem;
  private HomeViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewModel = new HomeViewModel(this);
    GridView mGridView = findViewById(R.id.grid_view);

    LightArrayAdapter adapter = new LightArrayAdapter(this, viewModel.getLedLists());
    mGridView.setAdapter(adapter);
    mGridView.setOnItemClickListener((adapterView, view, i, l) -> {
      viewModel.setSelectedLed(i);
      ColorSettingDialog dialog = new ColorSettingDialog(viewModel);
      dialog.show(getSupportFragmentManager(), "setting-dialog");
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    mConnectMenuItem = menu.findItem(R.id.connect);
    addDisposable(viewModel.getConnected()
        .subscribe(connected -> runOnUiThread(() -> mConnectMenuItem.setIcon(
            connected ? R.drawable.ic_connect : R.drawable.ic_disconnect))));
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.setting) {
      gotoSetting();
      return true;
    } else if (item.getItemId() == R.id.connect) {
      onConnectClick();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void gotoSetting() {
    Intent intent = new Intent(this, SettingActivity.class);
    startActivity(intent);
  }

  private void onConnectClick() {
    viewModel.onConnectClick();
  }
}
