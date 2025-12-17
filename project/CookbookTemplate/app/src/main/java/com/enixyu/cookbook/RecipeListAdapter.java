package com.enixyu.cookbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {

  private static final int ITEM_LAYOUT = R.layout.item_recip;
  private final List<Recipe> mData;

  public RecipeListAdapter(@NonNull Context context, List<Recipe> objects) {
    super(context, ITEM_LAYOUT, objects);
    mData = objects;
  }

  @Override
  public int getCount() {
    return mData.size();
  }

  // TODO: 构造每一行的视图，可参考列表页面作业的代码  (参考列表练习代码)
  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

  }
}
