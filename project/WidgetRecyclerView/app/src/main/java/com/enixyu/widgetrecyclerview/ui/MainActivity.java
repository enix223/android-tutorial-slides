package com.enixyu.widgetrecyclerview.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.enixyu.widgetrecyclerview.R;
import com.enixyu.widgetrecyclerview.model.MemoryProductRepository;
import com.enixyu.widgetrecyclerview.model.Product;
import com.enixyu.widgetrecyclerview.model.ProductRepository;
import com.enixyu.widgetrecyclerview.ui.ProductListViewAdapter.OnItemClickListener;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

  private ProductRepository mProductRepository;
  private ProductListViewAdapter mAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.list_view);
    var lm = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(lm);

    mProductRepository = new MemoryProductRepository();
    mAdapter = new ProductListViewAdapter(mProductRepository, this);
    recyclerView.setAdapter(mAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_create) {
      var newProduct = new Product(0, "产品" + RandomStringUtils.randomAlphabetic(3),
          RandomUtils.nextFloat(0, 100));
      mProductRepository.insert(newProduct);
      var last = mProductRepository.count();
      mAdapter.notifyItemInserted(last - 1);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(int position) {
    var product = mProductRepository.getByIndex(position);
    if (product == null) {
      return;
    }
    Toast.makeText(this, "点击了" + product.getName(), Toast.LENGTH_SHORT).show();
  }
}
