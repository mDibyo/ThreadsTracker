package com.diyboy.threadstracker.models;


import android.content.ContentValues;

public interface DatabaseSyncable {
    ContentValues toContentValues();

    boolean acquireWriteLock(boolean blocking);

    void releaseWriteLock();

    boolean isSaved();

    void setSaved(boolean saved);
}
