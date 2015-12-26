package com.diyboy.threadstracker.models.timechunks;


import android.support.annotation.NonNull;

import com.diyboy.threadstracker.models.threads.Task;
import org.joda.time.*;

public class TimeChunk implements Comparable<TimeChunk> {
    public static final Duration DEFAULT_DURATION = new Duration(Duration.standardMinutes(15));

    private ReadableInterval mInterval;
    private Task mAssignedTask = null;

    public TimeChunk(DateTime startTime, DateTime endTime) {
        this.mInterval = new Interval(startTime, endTime);
    }

    public TimeChunk(DateTime startTime, Duration duration) {
        this.mInterval = new Interval(startTime, duration);
    }

    public TimeChunk(DateTime startTime) {
        this(startTime, DEFAULT_DURATION);
    }

    public TimeChunk(ReadableInterval interval) {
        this.mInterval = interval;
    }

    public int compareTo(@NonNull TimeChunk other) {
        if (mInterval.isBefore(other.mInterval)) {
            return -1;
        }
        if (mInterval.isAfter(other.mInterval)) {
            return 1;
        }
        return 0;
    }

    public boolean equals(@NonNull TimeChunk other) {
        return mInterval.equals(other.mInterval);
    }

    public int hashCode() {
        return mInterval.hashCode();
    }

    public ReadableInterval getInterval() {
        return mInterval;
    }

    public Task getAssignedTask() {
        return mAssignedTask;
    }

    public boolean isAssigned() {
        return mAssignedTask != null;
    }

    public boolean assignTask(Task task) {
        if (mAssignedTask != null && task.getUuid() != mAssignedTask.getUuid()) {
            return false;
        }
        mAssignedTask = task;
        return true;
    }

    public Task unassignTask() {
        Task unassignedTask = mAssignedTask;
        mAssignedTask = null;
        return unassignedTask;
    }

}
