package com.enixyu.widgetrecyclerview.model;

import androidx.annotation.Nullable;

public interface Repository<T, I> {

  @Nullable
  T get(I id);

  @Nullable
  T getByIndex(int index);

  int count();

  void insert(T entity);

  void update(T entity);

  void delete(T entity);
}
