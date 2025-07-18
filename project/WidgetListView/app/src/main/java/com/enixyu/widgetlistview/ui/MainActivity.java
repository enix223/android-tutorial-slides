package com.enixyu.widgetlistview.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.widgetlistview.R;
import com.enixyu.widgetlistview.adapter.ListViewAdapterWithViewHolder;
import com.enixyu.widgetlistview.model.DataStore;
import com.enixyu.widgetlistview.model.Movie;
import com.enixyu.widgetlistview.model.MovieDataStore;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

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
    listView.setOnItemClickListener(this);
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

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    var entity = mDataStore.getItem(i);
    if (entity == null) {
      return;
    }
    Toast.makeText(this, "点击了" + entity.getTitle(), Toast.LENGTH_SHORT).show();
  }
}
