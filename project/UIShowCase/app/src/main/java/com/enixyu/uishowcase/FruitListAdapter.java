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

  public FruitListAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
    super(context, resource, objects);
    this.fruits = objects;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View item = LayoutInflater.from(getContext()).inflate(R.layout.item_fruit, parent);
    TextView nameTextView = item.findViewById(R.id.tv_name);
    TextView iconTextView = item.findViewById(R.id.tv_icon);
    Fruit fruit = getItem(position);
    nameTextView.setText(fruit.getName());
    iconTextView.setText(fruit.getIcon());
    return item;
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

  @Override
  public long getItemId(int position) {
    return getItem(position).getId();
  }
}
