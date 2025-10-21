package com.enixyu.uishowcase;

import android.os.Bundle;

import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.enixyu.uishowcase.model.Fruit;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_list_view);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    List<Fruit> fruits = new ArrayList<>();
    fruits.add(new Fruit(1, "西瓜", "🍉"));
    fruits.add(new Fruit(1, "苹果", "🍎"));
    fruits.add(new Fruit(1, "梨", "🍐"));
    ListView listView = findViewById(R.id.list_view);
    listView.setAdapter(new FruitListAdapter(this, R.layout.item_fruit, fruits));
  }
}