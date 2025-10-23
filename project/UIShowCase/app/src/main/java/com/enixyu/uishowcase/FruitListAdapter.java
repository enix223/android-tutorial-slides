package com.enixyu.uishowcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.uishowcase.model.Fruit;
import java.util.List;

public class FruitListAdapter extends ArrayAdapter<Fruit> {

  private final List<Fruit> fruits;
  private int mItemViewId;

  public FruitListAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
    super(context, resource, objects);
    this.fruits = objects;
    this.mItemViewId = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(mItemViewId, parent, false);
      TextView nameTextView = convertView.findViewById(R.id.tv_name);
      TextView iconTextView = convertView.findViewById(R.id.tv_icon);
      viewHolder = new ViewHolder(nameTextView, iconTextView);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    Fruit fruit = fruits.get(position);
    viewHolder.name.setText(fruit.getName());
    viewHolder.icon.setText(fruit.getIcon());
    return convertView;
  }

  @Override
  public int getCount() {
    return fruits.size();
  }

  @Nullable
  @Override
  public Fruit getItem(int position) {
    return fruits.get(position);
  }

  private static class ViewHolder {
    private TextView name;
    private TextView icon;

    public ViewHolder(TextView name, TextView icon) {
      this.name = name;
      this.icon = icon;
    }
  }
}
