package com.diyboy.threadstracker.models.timechunks;


import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.diyboy.threadstracker.models.BaseDatabaseSyncable;
import com.diyboy.threadstracker.models.threads.Task;

import org.joda.time.*;

public class TimeChunk extends BaseDatabaseSyncable implements Comparable<TimeChunk> {
    public static final Duration DEFAULT_DURATION = new Duration(Duration.standardMinutes(15));

    private ReadableInterval mInterval;
    private Task mAssignedTask = null;

    private TimeChunk(boolean saved, DateTime startTime, DateTime endTime) {
        super(saved);
        mInterval = new Interval(startTime, endTime);
    }

    public TimeChunk(DateTime startTime, DateTime endTime) {
        this(false, startTime, endTime);
    }

    public TimeChunk(DateTime startTime, Duration duration) {
        super(false);
        mInterval = new Interval(startTime, duration);
    }

    public TimeChunk(DateTime startTime) {
        this(startTime, DEFAULT_DURATION);
    }

    public TimeChunk(ReadableInterval interval) {
        super(false);
        this.mInterval = interval;
    }

    public static TimeChunk fromDatabaseCursor(Cursor c) {
        DateTime intervalStart = DateTime.parse(c.getString(c.getColumnIndex(
                TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_INTERVAL_START)));
        DateTime intervalEnd = DateTime.parse(c.getString(c.getColumnIndex(
                TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_INTERVAL_END)));
        return new TimeChunk(true, intervalStart, intervalEnd);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put(
                TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_INTERVAL_START,
                mInterval.getStart().toString());
        contentValues.put(
                TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_INTERVAL_END,
                mInterval.getEnd().toString());
        if (mAssignedTask != null) {
            contentValues.put(
                    TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_ASSIGNED_TASK_UUID,
                    mAssignedTask.getUuid().toString());
        }
        return contentValues;
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
        acquireWriteLock(true);
        setSaved(false);
        mAssignedTask = task;
        releaseWriteLock();
        return true;
    }

    public Task unassignTask() {
        acquireWriteLock(true);
        setSaved(false);
        Task unassignedTask = mAssignedTask;
        mAssignedTask = null;
        releaseWriteLock();
        return unassignedTask;
    }

}
