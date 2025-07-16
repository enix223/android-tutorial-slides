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

public class ListViewAdapterWithConvertView extends ArrayAdapter<Movie> {

  private final int mItemResourceId;
  private final DataStore<Movie, Integer> mDataStore;

  public ListViewAdapterWithConvertView(@NonNull Context context, int resource,
      DataStore<Movie, Integer> dataStore) {
    super(context, resource);
    mDataStore = dataStore;
    mItemResourceId = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view;
    if (convertView == null) {
      view = LayoutInflater.from(getContext()).inflate(mItemResourceId, parent, false);
    } else {
      view = convertView;
    }
    TextView textView = view.findViewById(R.id.list_item_label_title);
    Movie item = mDataStore.getItem(position);
    textView.setText(item.getTitle());
    return view;
  }

  @Override
  public int getCount() {
    return mDataStore.count();
  }
}