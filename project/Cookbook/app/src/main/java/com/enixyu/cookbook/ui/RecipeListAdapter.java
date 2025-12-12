package com.enixyu.cookbook.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.cookbook.R;
import com.enixyu.cookbook.model.Recipe;
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

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // 转换每行的布局为view对象
    View itemView = LayoutInflater.from(getContext()).inflate(ITEM_LAYOUT, parent, false);

    // 获取组件引用
    TextView tvTitle = itemView.findViewById(R.id.tv_title);
    TextView tvDesc = itemView.findViewById(R.id.tv_desc);

    // 获取每行的记录
    Recipe recipe = mData.get(position);
    tvTitle.setText(recipe.title);
    tvDesc.setText(recipe.description);

    return itemView;
  }
}
