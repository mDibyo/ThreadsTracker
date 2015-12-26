package com.diyboy.threadstracker.models.threads;

import com.diyboy.threadstracker.models.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

public abstract class AbstractTask implements Task {
    private UUID mUuid;
    private Thread mThread;
    private boolean mDone;
    private String mTitle;
    private double mImportance;
    private SortedSet<TimeChunk> mAllocatedTimeChunks;

    protected AbstractTask(UUID uuid, String title, double importance) {
        mTitle = title;
        mImportance = importance;
        mDone = false;
        mAllocatedTimeChunks = new TreeSet<>();
    }

    public AbstractTask(String title, double importance) {
        this(UUID.randomUUID(), title, importance);
    }

    @Override public UUID getUuid() {
        return mUuid;
    }

    @Override public Thread getThread() {
        return mThread;
    }

    @Override public void setThread(Thread thread) {
        mThread = thread;
    }

    @Override public String getTitle() {
        return mTitle;
    }

    @Override public void setTitle(String title) {
        mTitle = title;
    }

    @Override public double getImportance() {
        return mImportance;
    }

    @Override public void setImportance(double importance) {
        mImportance = importance;
    }

    @Override public boolean isDone() {
        return mDone;
    }

    @Override public void setDone(boolean done) {
        mDone = done;
    }

    @Override public SortedSet<TimeChunk> getAllocatedTimeChunks() {
        return mAllocatedTimeChunks;
    }
}
