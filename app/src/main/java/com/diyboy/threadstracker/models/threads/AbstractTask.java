package com.diyboy.threadstracker.models.threads;

import android.content.ContentValues;

import com.diyboy.threadstracker.models.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

public abstract class AbstractTask implements Task {
    protected UUID mUuid;
    protected Thread mThread;
    protected boolean mDone;
    protected String mTitle;
    protected double mImportance;
    protected SortedSet<TimeChunk> mAllocatedTimeChunks;

    protected boolean mSaved;

    protected AbstractTask(boolean saved, UUID uuid, String title, double importance) {
        mSaved = saved;
        mUuid = uuid;
        mTitle = title;
        mImportance = importance;
        mDone = false;
        mAllocatedTimeChunks = new TreeSet<>();
    }

    public AbstractTask(String title, double importance) {
        this(false, UUID.randomUUID(), title, importance);
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_UUID, mUuid.toString());
        contentValues.put(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_THREAD_UUID,
                mThread.getUuid().toString());
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_TITLE, mTitle);
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IMPORTANCE, mImportance);
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_DONE, mDone);
        return contentValues;
    }


    @Override
    public boolean isSaved() {
        return mSaved;
    }

    @Override
    public void setSaved(boolean saved) {
        mSaved = saved;
    }

    @Override
    public UUID getUuid() {
        return mUuid;
    }

    @Override
    public Thread getThread() {
        return mThread;
    }

    @Override
    public void setThread(Thread thread) {
        mSaved = false;
        mThread = thread;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void setTitle(String title) {
        mSaved = false;
        mTitle = title;
    }

    @Override
    public double getImportance() {
        return mImportance;
    }

    @Override
    public void setImportance(double importance) {
        mSaved = false;
        mImportance = importance;
    }

    @Override
    public boolean isDone() {
        return mDone;
    }

    @Override
    public void setDone(boolean done) {
        mSaved = false;
        mDone = done;
    }

    @Override
    public SortedSet<TimeChunk> getAllocatedTimeChunks() {
        mSaved = false;
        return mAllocatedTimeChunks;
    }

    @Override
    public TimeChunk[] getAllocatedTimeChunksReadOnly() {
        return mAllocatedTimeChunks.toArray(new TimeChunk[mAllocatedTimeChunks.size()]);
    }
}
