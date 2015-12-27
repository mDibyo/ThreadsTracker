package com.diyboy.threadstracker.models;

import java.util.concurrent.locks.ReentrantLock;


public abstract class BaseDatabaseSyncable implements DatabaseSyncable {
    private ReentrantLock mWriteLock;
    private boolean mSaved;

    protected BaseDatabaseSyncable(boolean saved) {
        mWriteLock = new ReentrantLock();
        mSaved = saved;
    }

    public BaseDatabaseSyncable() {
        this(false);
    }

    @Override
    public boolean acquireWriteLock(boolean blocking) {
        if (!blocking) {
            return mWriteLock.tryLock();
        }
        mWriteLock.lock();
        return true;
    }

    @Override
    public void releaseWriteLock() {
        mWriteLock.unlock();
    }

    protected boolean writeLockIsHeldByCurrentThread() {
        return mWriteLock.isHeldByCurrentThread();
    }

    @Override
    public boolean isSaved() {
        return mSaved;
    }

    @Override
    public void setSaved(boolean saved) {
        mSaved = saved;
    }
}
