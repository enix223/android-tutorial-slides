package com.enixyu.widgetlistview.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.widgetlistview.R;
import com.enixyu.widgetlistview.adapter.ListViewAdapterWithViewHolder;
import com.enixyu.widgetlistview.model.DataStore;
import com.enixyu.widgetlistview.model.Movie;
import com.enixyu.widgetlistview.model.MovieDataStore;

public class MainActivity extends AppCompatActivity {

  // private ListViewAdapter mAdapter;
  // private ListViewAdapterWithConvertView mAdapter;
  private ListViewAdapterWithViewHolder mAdapter;
  private final DataStore<Movie, Integer> mDataStore = new MovieDataStore();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ListView listView = findViewById(R.id.list_view);
    // mAdapter = new ListViewAdapter(this, R.layout.list_item_movie, mDataStore);
    // mAdapter = new ListViewAdapterWithConvertView(this, R.layout.list_item_movie, mDataStore);
    mAdapter = new ListViewAdapterWithViewHolder(this, R.layout.list_item_movie, mDataStore);
    listView.setAdapter(mAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_main_add) {
      performAdd();
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  private void performAdd() {
    var id = mDataStore.getNextId();
    mDataStore.add(new Movie(id, "movie " + id));
    mAdapter.notifyDataSetChanged();
  }
}
