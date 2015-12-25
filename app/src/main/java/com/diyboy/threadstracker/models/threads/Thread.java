package com.diyboy.threadstracker.models.threads;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Thread {
  public static final double DEFAULT_IMPORTANCE = 5;

  private UUID uuid;
  private String title;
  private double importance;
  private Set<Task> tasks;

  public Thread(String title, double importance) {
    this.title = title;
    this.importance = importance;
    this.tasks = new HashSet<Task>();
  }

  public Thread(String title) {
    this(title, DEFAULT_IMPORTANCE);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String newTitle) {
    title = newTitle;
  }

  public double getImportance() {
    return importance;
  }

  public void setImportance(double newImportance) {
    importance = newImportance;
  }

  public Set<Task> getTasks() {
    return tasks;
  }
}
