package com.diyboy.threadstracker.models.threads;


import main.java.com.diyboy.threadstracker.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.UUID;

public interface Task {
  UUID getUuid();

  String getTitle();

  void setTitle(String title);

  double getImportance();

  void setImportance(double importance);

  boolean isDone();

  void setDone(boolean done);

  SortedSet<TimeChunk> getAllocatedTimeChunks();

}
