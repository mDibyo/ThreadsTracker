package com.diyboy.threadstracker.models.timechunks;


import android.support.annotation.NonNull;

import com.diyboy.threadstracker.models.threads.Task;
import org.joda.time.*;

public class TimeChunk implements Comparable<TimeChunk> {
    public static final Duration DEFAULT_DURATION = new Duration(Duration.standardMinutes(15));

    private ReadableInterval interval;
    private Task assignedTask = null;

    public TimeChunk(DateTime startTime, DateTime endTime) {
        this.interval = new Interval(startTime, endTime);
    }

    public TimeChunk(DateTime startTime, Duration duration) {
        this.interval = new Interval(startTime, duration);
    }

    public TimeChunk(DateTime startTime) {
        this(startTime, DEFAULT_DURATION);
    }

    public TimeChunk(ReadableInterval interval) {
        this.interval = interval;
    }

    public int compareTo(@NonNull TimeChunk other) {
        if (interval.isBefore(other.interval)) {
            return -1;
        }
        if (interval.isAfter(other.interval)) {
            return 1;
        }
        return 0;
    }

    public boolean equals(@NonNull TimeChunk other) {
        return interval.equals(other.interval);
    }

    public int hashCode() {
        return interval.hashCode();
    }

    public ReadableInterval getInterval() {
        return interval;
    }

    public Task getAssignedTask() {
        return assignedTask;
    }

    public boolean isAssigned() {
        return assignedTask != null;
    }

    public boolean assignTask(Task task) {
        if (assignedTask != null && task.getUuid() != assignedTask.getUuid()) {
            return false;
        }
        assignedTask = task;
        return true;
    }

    public Task unassignTask() {
        Task unassignedTask = assignedTask;
        assignedTask = null;
        return unassignedTask;
    }

}
