package com.enixyu.mqttcontroller.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import androidx.annotation.Nullable;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.ServiceProvider;
import com.enixyu.mqttcontroller.model.MqttSettingModel;
import com.enixyu.mqttcontroller.store.StoreException;

public class SettingActivity extends BaseActivity {

  private TextFieldView mBrokerUrlField;
  private TextFieldView mUsernameField;
  private TextFieldView mPasswordField;
  private TextFieldView mDeviceIdField;
  private MqttSettingModel model = new MqttSettingModel();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);

    mBrokerUrlField = findViewById(R.id.field_broker_url);
    mUsernameField = findViewById(R.id.field_username);
    mPasswordField = findViewById(R.id.field_password);
    mDeviceIdField = findViewById(R.id.field_device_id);
    Button submit = findViewById(R.id.btn_save);

    submit.setOnClickListener(view -> submitForm());
    load();
  }

  private void load() {
    MqttSettingModel value = ServiceProvider.INSTANCE.mqttSettingStore.get(this);
    if (value != null) {
      model = value;
    }
    mBrokerUrlField.setText(model.getBrokerHost());
    mUsernameField.setText(model.getUsername());
    mPasswordField.setText(model.getPassword());
    mDeviceIdField.setText(model.getDeviceId());
  }

  private void submitForm() {
    String broker = mBrokerUrlField.getValue();
    if (TextUtils.isEmpty(broker)) {
      AlertHelper.showToast(this, R.string.mqtt_broker_missing);
      return;
    }

    model.setBrokerHost(broker);
    model.setUsername(mUsernameField.getValue());
    model.setPassword(mPasswordField.getValue());
    model.setDeviceId(mDeviceIdField.getValue());
    try {
      ServiceProvider.INSTANCE.mqttSettingStore.save(this, model);
      AlertHelper.showToast(this, R.string.save_success);
    } catch (StoreException e) {
      AlertHelper.showToast(this, R.string.save_failed);
    }
  }
}
