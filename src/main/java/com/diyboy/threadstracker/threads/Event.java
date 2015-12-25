package main.java.com.diyboy.threadstracker.threads;

import java.util.UUID;

public class Event implements Activity {
  private UUID uuid;
  private boolean done;
  private String title;
  private double importance;

  public Event(String title, double importance) {
    this.uuid = UUID.randomUUID();
    this.title = title;
    this.importance = importance;
    this.done = false;
  }

  public Event(String title) {
    this(title, 5);
  }

  public UUID getUuid() {
    return uuid;
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

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean newDone) {
    done = newDone;
  }

}
