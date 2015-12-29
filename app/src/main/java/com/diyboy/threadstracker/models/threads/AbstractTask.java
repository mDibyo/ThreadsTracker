package com.diyboy.threadstracker.models.threads;

import android.content.ContentValues;

import com.diyboy.threadstracker.models.BaseDatabaseSyncable;
import com.diyboy.threadstracker.models.timechunks.TimeChunk;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

abstract class AbstractTask extends BaseDatabaseSyncable implements Task {
    protected UUID mUuid;
    protected Thread mThread;
    protected boolean mDone;
    protected String mTitle;
    protected double mImportance;
    protected SortedSet<TimeChunk> mAllocatedTimeChunks;

    protected AbstractTask(boolean saved, UUID uuid, String title, double importance) {
        super(saved);
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
    public UUID getUuid() {
        return mUuid;
    }

    @Override
    public Thread getThread() {
        return mThread;
    }

    @Override
    public void setThread(Thread thread) {
        acquireWriteLock(true);
        setSaved(false);
        mThread = thread;
        releaseWriteLock();
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void setTitle(String title) {
        acquireWriteLock(true);
        setSaved(false);
        mTitle = title;
        releaseWriteLock();
    }

    @Override
    public double getImportance() {
        return mImportance;
    }

    @Override
    public void setImportance(double importance) {
        acquireWriteLock(true);
        setSaved(false);
        mImportance = importance;
        releaseWriteLock();
    }

    @Override
    public boolean isDone() {
        return mDone;
    }

    @Override
    public void setDone(boolean done) {
        acquireWriteLock(true);
        setSaved(false);
        mDone = done;
        releaseWriteLock();
    }

    @Override
    public SortedSet<TimeChunk> getAllocatedTimeChunks() {
        if (!writeLockIsHeldByCurrentThread()) {
            return null;
        }
        setSaved(false);
        return mAllocatedTimeChunks;
    }

    @Override
    public TimeChunk[] getAllocatedTimeChunksReadOnly() {
        return mAllocatedTimeChunks.toArray(new TimeChunk[mAllocatedTimeChunks.size()]);
    }
}
