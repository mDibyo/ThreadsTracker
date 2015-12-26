package com.diyboy.threadstracker.models.threads;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Thread {
    public static final double DEFAULT_IMPORTANCE = 5;

    private UUID mUuid;
    private String mTitle;
    private double mImportance;
    private Set<Task> mTasks;

    public Thread(String title, double importance) {
        mUuid = UUID.randomUUID();
        mTitle = title;
        mImportance = importance;
        mTasks = new HashSet<>();
    }

    public Thread(String title) {
        this(title, DEFAULT_IMPORTANCE);
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
