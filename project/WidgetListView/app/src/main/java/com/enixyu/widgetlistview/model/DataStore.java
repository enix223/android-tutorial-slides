package com.enixyu.widgetlistview.model;

import java.util.List;

public interface DataStore<E, I> {

  List<E> list();

  E getItem(int index);

  int count();

  E add(E entity);

  E update(E entity);

  void delete(E entity);

  I getNextId();
}
