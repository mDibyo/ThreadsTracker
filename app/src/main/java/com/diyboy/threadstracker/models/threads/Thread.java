package com.diyboy.threadstracker.models.threads;

import android.content.ContentValues;
import android.database.Cursor;

import com.diyboy.threadstracker.models.BaseDatabaseSyncable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Thread extends BaseDatabaseSyncable {
    public static final double DEFAULT_IMPORTANCE = 5;

    private UUID mUuid;
    private String mTitle;
    private double mImportance;
    private Set<Task> mTasks;

    private Thread(boolean saved, UUID uuid, String title, double importance) {
        super(saved);
        mUuid = uuid;
        mTitle = title;
        mImportance = importance;
        mTasks = new HashSet<>();
    }

    public Thread(String title, double importance) {
        this(false, UUID.randomUUID(), title, importance);
    }

    public Thread(String title) {
        this(title, DEFAULT_IMPORTANCE);
    }

    public static Thread fromDatabaseCursor(Cursor c) {
        UUID uuid = UUID.fromString(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_UUID)));
        String title = c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_TITLE));
        double importance = c.getDouble(c.getColumnIndex(
                ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_IMPORTANCE));
        return new Thread(true, uuid, title, importance);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put(ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_UUID, mUuid.toString());
        contentValues.put(ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_TITLE, mTitle);
        contentValues.put(ThreadsDatabaseContract.ThreadsTable.COLUMN_NAME_IMPORTANCE, mImportance);
        return contentValues;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        acquireWriteLock(true);
        setSaved(false);
        mTitle = title;
        releaseWriteLock();
    }

    public double getImportance() {
        return mImportance;
    }

    public void setImportance(double importance) {
        acquireWriteLock(true);
        setSaved(false);
        mImportance = importance;
        releaseWriteLock();
    }

    public Task[] getTasksReadOnly() {
        return mTasks.toArray(new Task[mTasks.size()]);
    }

    public Set<Task> getTasks() {
        if (!writeLockIsHeldByCurrentThread()) {
            return null;
        }
        setSaved(false);
        return mTasks;
    }
}
