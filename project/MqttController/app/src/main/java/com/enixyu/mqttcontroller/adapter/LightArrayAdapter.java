package com.enixyu.mqttcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.mqttcontroller.R;
import com.enixyu.mqttcontroller.model.LED;
import java.util.List;

public class LightArrayAdapter extends ArrayAdapter<LED> {

  @LayoutRes
  private static final int ITEM_LAYOUT = R.layout.item_led;
  private final List<LED> ledList;

  public LightArrayAdapter(@NonNull Context context, List<LED> ledList) {
    super(context, ITEM_LAYOUT);
    this.ledList = ledList;
  }

  @Override
  public int getCount() {
    return ledList.size();
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(ITEM_LAYOUT, parent, false);
      viewHolder = new ViewHolder(convertView.findViewById(R.id.led_bg),
          convertView.findViewById(R.id.led_label));
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    LED item = ledList.get(position);
    viewHolder.ledLabel.setText(String.valueOf(item.getId()));
    viewHolder.ledBg.setBackgroundColor(item.getColor());
    return convertView;
  }

  static class ViewHolder {

    private final LinearLayout ledBg;
    private final TextView ledLabel;

    ViewHolder(LinearLayout ledBg, TextView ledLabel) {
      this.ledBg = ledBg;
      this.ledLabel = ledLabel;
    }
  }
}
