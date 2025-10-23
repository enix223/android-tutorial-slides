package com.enixyu.uishowcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.uishowcase.model.Recipe;
import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {

  private final List<Recipe> mData;

  private final int mItemViewId;

  public RecipeListAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
    super(context, resource, objects);
    mData = objects;
    mItemViewId = resource;
  }


  @Override
  public int getCount() {
    return mData.size();
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // 转换每行的布局为view对象
    View itemView = LayoutInflater.from(getContext()).inflate(mItemViewId, parent, false);

    // 获取组件引用
    ImageView imgBg = itemView.findViewById(R.id.img_bg);
    TextView tvTitle = itemView.findViewById(R.id.tv_title);
    TextView tvDesc = itemView.findViewById(R.id.tv_desc);

    // 获取每行的记录
    Recipe recipe = mData.get(position);
    imgBg.setImageResource(recipe.image);
    tvTitle.setText(recipe.title);
    tvDesc.setText(recipe.desc);

    return itemView;
  }
}
