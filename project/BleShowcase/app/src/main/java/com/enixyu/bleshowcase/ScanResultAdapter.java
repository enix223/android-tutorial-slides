package com.enixyu.bleshowcase;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScanResultAdapter extends ArrayAdapter<ScanResult> {

  private static final int ITEM_LAYOUT = R.layout.item_scan_result;
  private final List<ScanResult> mResults = new ArrayList<>();

  public ScanResultAdapter(@NonNull Context context) {
    super(context, ITEM_LAYOUT);
  }

  @Override
  public int getCount() {
    return mResults.size();
  }

  @NonNull
  @Override
  @SuppressLint("MissingPermission")
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(ITEM_LAYOUT, parent, false);
      TextView tvDeviceName = convertView.findViewById(R.id.tv_device_name);
      TextView tvRSSI = convertView.findViewById(R.id.tv_rssi);
      viewHolder = new ViewHolder(tvDeviceName, tvRSSI);
      convertView.setTag(viewHolder);
    } else {
      viewHolder= (ViewHolder) convertView.getTag();
    }

    ScanResult item = mResults.get(position);
    BluetoothDevice device = item.getDevice();
    viewHolder.tvDeviceName.setText(device.getName() == null || device.getName().isBlank()
        ? device.getAddress()
        : device.getName());
    viewHolder.tvRSSI.setText(String.valueOf(item.getRssi()));
    return convertView;
  }

  /// 添加数据
  public void addItem(ScanResult item) {
    boolean found = mResults.stream()
        .anyMatch(e -> Objects.equals(e.getDevice().getAddress(), item.getDevice().getAddress()));
    if (found) {
      return;
    }
    mResults.add(item);
    notifyDataSetChanged();
  }

  /// 清空数据
  public void clear() {
    mResults.clear();
    notifyDataSetChanged();
  }

  static class ViewHolder {
    private final TextView tvDeviceName;
    private final TextView tvRSSI;

    public ViewHolder(TextView tvDeviceName, TextView tvRSSI) {
      this.tvDeviceName = tvDeviceName;
      this.tvRSSI = tvRSSI;
    }
  }
}
