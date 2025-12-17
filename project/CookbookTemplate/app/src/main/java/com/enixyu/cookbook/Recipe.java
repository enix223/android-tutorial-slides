package com.enixyu.cookbook;

public class Recipe {

  public int id;
  public String title;
  public String description;
  public float level;
  public int timeCost;

  public Recipe(int id, String title, String description, float level, int timeCost) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.level = level;
    this.timeCost = timeCost;
  }
}
