package com.diyboy.threadstracker.models.threads;

import android.database.Cursor;

import com.diyboy.threadstracker.models.DatabaseContract;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Thread {
    public static final double DEFAULT_IMPORTANCE = 5;

    private UUID mUuid;
    private String mTitle;
    private double mImportance;
    private Set<Task> mTasks;

    private Thread(UUID uuid, String title, double importance) {
        mUuid = uuid;
        mTitle = title;
        mImportance = importance;
        mTasks = new HashSet<>();
    }

    public Thread(String title, double importance) {
        this(UUID.randomUUID(), title, importance);
    }

    public Thread(String title) {
        this(title, DEFAULT_IMPORTANCE);
    }

    public static Thread fromDatabaseCursor(Cursor c) {
        UUID uuid = UUID.fromString(c.getString(c.getColumnIndex(
                DatabaseContract.ThreadsTable.COLUMN_NAME_UUID)));
        String title = c.getString(c.getColumnIndex(
                DatabaseContract.ThreadsTable.COLUMN_NAME_TITLE));
        double importance = c.getDouble(c.getColumnIndex(
                DatabaseContract.ThreadsTable.COLUMN_NAME_IMPORTANCE));
        return new Thread(uuid, title, importance);
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getImportance() {
        return mImportance;
    }

    public void setImportance(double importance) {
        mImportance = importance;
    }

    public Set<Task> getTasks() {
        return mTasks;
    }
}
