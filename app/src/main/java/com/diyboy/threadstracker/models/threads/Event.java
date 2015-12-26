package com.diyboy.threadstracker.models.threads;


import android.database.Cursor;

import com.diyboy.threadstracker.models.DatabaseContract;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableInterval;

import java.util.UUID;

public class Event extends AbstractTask {
    public static final double DEFAULT_EVENT_IMPORTANCE = 11;

    private ReadableInterval mInterval;

    protected Event(UUID uuid, String title, ReadableInterval interval, double importance) {
        super(uuid, title, importance);
        mInterval = interval;
    }

    public Event(String title, ReadableInterval interval, double importance) {
        this(UUID.randomUUID(), title, interval, importance);
    }

    public Event(String title, ReadableInterval interval) {
        this(title, interval, DEFAULT_EVENT_IMPORTANCE);
    }

    public static Event fromDatabaseCursor(Cursor c) {
        UUID uuid = UUID.fromString(c.getString(c.getColumnIndex(
                DatabaseContract.TasksTable.COLUMN_NAME_UUID)));
        String title = c.getString(c.getColumnIndex(
                DatabaseContract.TasksTable.COLUMN_NAME_TITLE));
        double importance = c.getDouble(c.getColumnIndex(
                DatabaseContract.TasksTable.COLUMN_NAME_IMPORTANCE));
        DateTime intervalStart = DateTime.parse(c.getString(c.getColumnIndex(
                DatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_START)));
        DateTime intervalEnd = DateTime.parse(c.getString(c.getColumnIndex(
                DatabaseContract.TasksTable.COLUMN_NAME_EVENT_INTERVAL_END)));
        ReadableInterval interval = new Interval(intervalStart, intervalEnd);
        return new Event(uuid, title, interval, importance);
    }

    public ReadableInterval getInterval() {
        return mInterval;
    }

    public void setInterval(ReadableInterval interval) {
        mInterval = interval;
    }
}
