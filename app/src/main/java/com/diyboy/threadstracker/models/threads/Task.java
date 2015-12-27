package com.diyboy.threadstracker.models.threads;

import com.diyboy.threadstracker.models.DatabaseSyncable;
import com.diyboy.threadstracker.models.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.UUID;

public interface Task extends DatabaseSyncable {
    UUID getUuid();

    void setThread(Thread thread);

    Thread getThread();

    String getTitle();

    void setTitle(String title);

    double getImportance();

    void setImportance(double importance);

    boolean isDone();

    void setDone(boolean done);

    SortedSet<TimeChunk> getAllocatedTimeChunks();

    TimeChunk[] getAllocatedTimeChunksReadOnly();
}
