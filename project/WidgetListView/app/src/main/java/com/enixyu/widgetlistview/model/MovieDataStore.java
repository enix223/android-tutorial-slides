package com.enixyu.widgetlistview.model;

import com.enixyu.widgetlistview.common.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieDataStore implements DataStore<Movie, Integer> {

  private final AtomicInteger id = new AtomicInteger(0);
  private final ArrayList<Movie> data = new ArrayList<>();

  @Override
  public List<Movie> list() {
    return data;
  }

  @Override
  public Movie getItem(int index) {
    if (index < 0 || index >= data.size()) {
      throw new IndexOutOfBoundsException("下标越界了!");
    }
    return data.get(index);
  }

  @Override
  public int count() {
    return data.size();
  }

  @Override
  public Movie add(Movie entity) {
    data.add(entity);
    return entity;
  }

  @Override
  public Movie update(Movie entity) {
    var index = getItem(entity);
    var item = data.get(index);
    item.setTitle(entity.getTitle());
    return item;
  }

  @Override
  public void delete(Movie entity) {
    var index = getItem(entity);
    data.remove(index);
  }

  @Override
  public Integer getNextId() {
    return id.incrementAndGet();
  }

  private int getItem(Movie entity) {
    for (int i = 0; i < data.size(); i++) {
      if (entity.getId() == data.get(i).getId()) {
        return i;
      }
    }
    throw new RecordNotFoundException(String.format("记录不存在: %d", entity.getId()));
  }
}
