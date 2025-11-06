package com.enixyu.bleshowcase;

import android.annotation.SuppressLint;
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

public class ScanResultAdapter extends ArrayAdapter<ScanResult> {

  private int mItemId;
  private List<ScanResult> mResults = new ArrayList<>();

  public ScanResultAdapter(@NonNull Context context, int resource) {
    super(context, resource);
    mItemId = resource;
  }

  @Override
  public int getCount() {
    return mResults.size();
  }

  @NonNull
  @Override
  @SuppressLint("MissingPermission")
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View itemView = LayoutInflater.from(getContext()).inflate(mItemId, parent, false);
    TextView tvDeviceName = itemView.findViewById(R.id.tv_device_name);
    ScanResult item = mResults.get(position);
    tvDeviceName.setText(item.getDevice().getName());
    return tvDeviceName;
  }

  public void addItem(ScanResult item) {
    mResults.add(item);
    notifyDataSetChanged();
  }
}
