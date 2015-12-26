package com.diyboy.threadstracker.models.threads;

import com.diyboy.threadstracker.models.timechunks.TimeChunk;

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
        this.allocatedTimeChunks = new TreeSet<>();
    }

    @Override public UUID getUuid() {
        return uuid;
    }

    @Override public String getTitle() {
        return title;
    }

    @Override public void setTitle(String newTitle) {
        title = newTitle;
    }

    @Override public double getImportance() {
        return importance;
    }

    @Override public void setImportance(double newImportance) {
        importance = newImportance;
    }

    @Override public boolean isDone() {
        return done;
    }

    @Override public void setDone(boolean newDone) {
        done = newDone;
    }

    @Override public SortedSet<TimeChunk> getAllocatedTimeChunks() {
        return allocatedTimeChunks;
    }
}
