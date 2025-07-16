package com.enixyu.widgetlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enixyu.widgetlistview.model.DataStore;
import com.enixyu.widgetlistview.R;
import com.enixyu.widgetlistview.model.Movie;

public class ListViewAdapterWithViewHolder extends ArrayAdapter<Movie> {

  private final int mItemResourceId;
  private final DataStore<Movie, Integer> mDataStore;

  public ListViewAdapterWithViewHolder(@NonNull Context context, int resource,
      DataStore<Movie, Integer> mDataStore) {
    super(context, resource);
    this.mItemResourceId = resource;
    this.mDataStore = mDataStore;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view;
    ViewHolder viewHolder;
    if (convertView == null) {
      view = LayoutInflater.from(getContext()).inflate(mItemResourceId, parent, false);
      viewHolder = new ViewHolder(view.findViewById(R.id.list_item_label_title));
      view.setTag(viewHolder);
    } else {
      view = convertView;
      viewHolder = (ViewHolder) view.getTag();
    }

    Movie item = mDataStore.getItem(position);
    viewHolder.mLabel.setText(item.getTitle());

    return view;
  }

  @Override
  public int getCount() {
    return mDataStore.count();
  }

  public static final class ViewHolder {

    private final TextView mLabel;

    public ViewHolder(TextView mLabel) {
      this.mLabel = mLabel;
    }

    public TextView getmLabel() {
      return mLabel;
    }
  }
}
