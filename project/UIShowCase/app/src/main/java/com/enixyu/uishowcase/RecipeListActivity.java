package com.enixyu.uishowcase;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.enixyu.uishowcase.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);

    ListView listView = findViewById(R.id.recipe_list);
    List<Recipe> recipes = new ArrayList<>();
    recipes.add(new Recipe(1, "水果沙拉", "水果沙拉有助于减肥", R.drawable.salad));
    recipes.add(new Recipe(2, "番茄炒蛋", "家常下饭菜，营养又简单", R.drawable.meat));
    recipes.add(new Recipe(3, "麻婆豆腐", "麻辣鲜香，米饭最佳搭档", R.drawable.salad));
    recipes.add(new Recipe(4, "红烧排骨", "浓油赤酱，骨酥肉烂入味", R.drawable.meat));
    recipes.add(new Recipe(5, "拍黄瓜", "清爽脆嫩，夏日开胃小凉菜", R.drawable.meat));
    recipes.add(new Recipe(6, "清蒸鲈鱼", "肉质鲜嫩，原汁原味健康菜", R.drawable.salad));
    recipes.add(new Recipe(7, "油焖大虾", "咸鲜红亮，壳酥肉嫩味醇", R.drawable.meat));
    recipes.add(new Recipe(8, "地三鲜", "东北家常味，土豆茄子椒香", R.drawable.salad));

    RecipeListAdapter adapter = new RecipeListAdapter(this, R.layout.item_recipe, recipes);
    listView.setAdapter(adapter);
  }
}
