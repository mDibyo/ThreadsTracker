package com.diyboy.threadstracker.models.timechunks;


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

    public int compareTo(TimeChunk other) {
        if (interval.equals(other.interval)) {
            return 0;
        }
        if (interval.getEnd().compareTo(other.interval.getStart()) < 0) {
            return -1;
        }
        if (interval.getStart().compareTo(other.interval.getEnd()) > 0) {
            return 1;
        }
        return 0;
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
