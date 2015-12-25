package main.java.com.diyboy.threadstracker.threads;

import main.java.com.diyboy.threadstracker.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

public abstract class AbstractTask implements Task {
  private UUID uuid;
  private boolean done;
  private String title;
  private double importance;
  private SortedSet<TimeChunk> allocatedTimeChunks;

  public AbstractTask(String title, double importance) {
    this.uuid = UUID.randomUUID();
    this.title = title;
    this.importance = importance;
    this.done = false;
    this.allocatedTimeChunks = new TreeSet<TimeChunk>();
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

  public SortedSet<TimeChunk> getAllocatedTimeChunks() {
    return allocatedTimeChunks;
  }
}
