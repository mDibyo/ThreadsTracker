package com.diyboy.threadstracker.models.threads;


import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableInterval;

import java.util.UUID;

public class Event extends AbstractTask {
    public static final double DEFAULT_EVENT_IMPORTANCE = 11;

    private ReadableInterval mInterval;

    protected Event(boolean saved, UUID uuid, String title, ReadableInterval interval, double importance) {
        super(saved, uuid, title, importance);
        mInterval = interval;
    }

    public Event(String title, ReadableInterval interval, double importance) {
        this(false, UUID.randomUUID(), title, interval, importance);
    }

    public Event(String title, ReadableInterval interval) {
        this(title, interval, DEFAULT_EVENT_IMPORTANCE);
    }

    public static Event fromDatabaseCursor(Cursor c) {
        UUID uuid = UUID.fromString(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_UUID)));
        String title = c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_TITLE));
        double importance = c.getDouble(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IMPORTANCE));
        DateTime intervalStart = DateTime.parse(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_START)));
        DateTime intervalEnd = DateTime.parse(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_END)));
        ReadableInterval interval = new Interval(intervalStart, intervalEnd);
        return new Event(true, uuid, title, interval, importance);
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = super.toContentValues();
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IS_EVENT, true);
        contentValues.put(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_START,
                mInterval.getStart().toString());
        contentValues.put(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_END,
                mInterval.getEnd().toString());
        return contentValues;
    }

    public ReadableInterval getInterval() {
        return mInterval;
    }

    public void setInterval(ReadableInterval interval) {
        acquireWriteLock(true);
        setSaved(false);
        mInterval = interval;
        releaseWriteLock();
    }
}
