package com.enixyu.uishowcase.model;

import androidx.annotation.DrawableRes;

public class Recipe {

  public int id;

  public String title;

  public String desc;

  @DrawableRes
  public int image;

  public Recipe(int id, String title, String desc, int image) {
    this.id = id;
    this.title = title;
    this.desc = desc;
    this.image = image;
  }
}
