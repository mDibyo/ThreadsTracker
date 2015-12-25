package com.diyboy.threadstracker.models.threads;


import com.diyboy.threadstracker.models.timechunks.TimeChunk;

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
