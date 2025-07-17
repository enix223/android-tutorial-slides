package com.enixyu.widgetrecyclerview.model;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryProductRepository implements ProductRepository {

  private final AtomicInteger mIdSequence = new AtomicInteger(0);
  private final List<Product> mData = new ArrayList<>();

  @Nullable
  @Override
  public Product get(Integer id) {
    return mData.stream().filter(e -> e.getId() == id)
        .findFirst()
        .orElse(null);
  }

  @Nullable
  @Override
  public Product getByIndex(int index) {
    return mData.get(index);
  }

  @Override
  public int count() {
    return mData.size();
  }

  @Override
  public void insert(Product entity) {
    entity.setId(mIdSequence.incrementAndGet());
    mData.add(entity);
  }

  @Override
  public void update(Product entity) {
    var product = get(entity.getId());
    if (product == null) {
      throw new RuntimeException("产品不存在");
    }
    product.setName(entity.getName());
    product.setPrice(entity.getPrice());
  }

  @Override
  public void delete(Product entity) {
    mData.removeIf(e -> e.getId() == entity.getId());
  }
}
