package com.enixyu.mqttcontroller.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.ServiceProvider;
import com.enixyu.mqttcontroller.adapter.LightArrayAdapter;
import com.enixyu.mqttcontroller.controller.ConnectOptions;
import com.enixyu.mqttcontroller.controller.OnConnectionChangedListener;
import com.enixyu.mqttcontroller.controller.OnDevicePropertiesChangedListener;
import com.enixyu.mqttcontroller.controller.RemoteControlException;
import com.enixyu.mqttcontroller.controller.RemoteController;
import com.enixyu.mqttcontroller.model.LED;
import com.enixyu.mqttcontroller.model.MqttSettingModel;
import com.enixyu.mqttcontroller.store.MqttSettingStore;
import com.enixyu.mqttcontroller.ui.ColorRingView.OnColorChangedListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements OnDevicePropertiesChangedListener,
    OnConnectionChangedListener {

  private RemoteController mRemoteController;
  private MqttSettingStore mSettingStore;
  private MqttSettingModel mSetting;
  private MenuItem mConnectMenuItem;
  private boolean mConnected = false;
  private int mSelectedLedIndex = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRemoteController = ServiceProvider.INSTANCE.remoteController;
    mSettingStore = ServiceProvider.INSTANCE.mqttSettingStore;

    ColorRingView mColorPicker = findViewById(R.id.color_picker);
    AppCompatSeekBar seekBar = findViewById(R.id.brightness);
    GridView mGridView = findViewById(R.id.grid_view);

    List<LED> ledLists = new ArrayList<>();
    int row = 8;
    int col = 8;
    for (int i = 1; i <= row; i++) {
      for (int j = 1; j <= col; j++) {
        ledLists.add(new LED(i, j, Color.BLACK));
      }
    }
    LightArrayAdapter adapter = new LightArrayAdapter(this, ledLists);
    mGridView.setAdapter(adapter);
    mGridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mSelectedLedIndex = i;
        AlertHelper.showToast(MainActivity.this, String.valueOf(i + 1));
      }
    });

    mColorPicker.setOnColorChangedListener(new OnColorChangedListener() {
      @Override
      public void onColorChanged(Color color) {
      }

      @Override
      public void onColorSelected(Color color) {
        Log.d(TAG, String.format("选择颜色变化: %s", String.format("%8x", color.toArgb())));
        setLightColor(color, seekBar.getProgress(), mSelectedLedIndex);
      }
    });

    seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Log.d(TAG, String.format("修改亮度: %d", seekBar.getProgress()));
        setLightColor(mColorPicker.getCurrentColor(), seekBar.getProgress(), mSelectedLedIndex);
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    mSetting = mSettingStore.get(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    mConnectMenuItem = menu.findItem(R.id.connect);
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

  @Override
  public void onConnectionChanged(boolean connected) {
    mConnected = connected;
    if (connected && !TextUtils.isEmpty(mSetting.getDeviceId())) {
      try {
        mRemoteController.subscribe(mSetting.getDeviceId());
      } catch (RemoteControlException ignored) {
      }
    }
    mConnectMenuItem.setIcon(connected ? R.drawable.ic_connect : R.drawable.ic_disconnect);
  }

  @Override
  public void onPropertiesChanged(String deviceId, Map<String, Object> properties) {

  }

  private void gotoSetting() {
    Intent intent = new Intent(this, SettingActivity.class);
    startActivity(intent);
  }

  private void onConnectClick() {
    if (mConnected) {
      disconnect();
    } else {
      connect();
    }
  }

  private void disconnect() {
    try {
      mRemoteController.disconnect();
    } catch (RemoteControlException e) {
      AlertHelper.showToast(this, e.getMessage());
    }
  }

  private void connect() {
    if (TextUtils.isEmpty(mSetting.getDeviceId())) {
      AlertHelper.showToast(this, R.string.mqtt_device_id_missing);
      return;
    }
    ConnectOptions opts = new ConnectOptions(mSetting.getUsername(), mSetting.getPassword(),
        mSetting.getBrokerHost(), mSetting.getBrokerPort(), this, this);
    try {
      mRemoteController.connect(this, opts);
    } catch (RemoteControlException e) {
      AlertHelper.showToast(this, e.getMessage());
    }
  }

  private void setLightColor(Color color, int brightness, int id) {
    if (!mConnected) {
      AlertHelper.showToast(this, R.string.mqtt_not_connect);
      return;
    }
    try {
      mRemoteController.setProperties(mSetting.getDeviceId(), Map.of(
          "i", id,
          "a", brightness,
          "r", (int) (color.red() * 255),
          "g", (int) (color.green() * 255),
          "b", (int) (color.blue() * 255))
      );
    } catch (RemoteControlException e) {
      AlertHelper.showToast(this, e.getMessage());
    }
  }
}
