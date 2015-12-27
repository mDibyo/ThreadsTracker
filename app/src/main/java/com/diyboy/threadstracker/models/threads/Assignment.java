package com.diyboy.threadstracker.models.threads;

import android.content.ContentValues;
import android.database.Cursor;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

import java.util.UUID;

public class Assignment extends AbstractTask {
    public static final double DEFAULT_ASSIGNMENT_IMPORTANCE = 5;

    private ReadableDateTime mDeadline;
    private ReadableDuration mRequiredDuration;

    protected Assignment(boolean saved, UUID uuid, String title, ReadableDateTime deadline, ReadableDuration requiredDuration, double importance) {
        super(saved, uuid, title, importance);
        mDeadline = deadline;
        mRequiredDuration = requiredDuration;
    }

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration, double importance) {
        this(false, UUID.randomUUID(), title, deadline, requiredDuration, importance);
    }

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration) {
        this(title, deadline, requiredDuration, DEFAULT_ASSIGNMENT_IMPORTANCE);
    }

    public static Assignment fromDatabaseCursor(Cursor c) {
        UUID uuid = UUID.fromString(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_UUID)));
        String title = c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_TITLE));
        double importance = c.getDouble(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IMPORTANCE));
        ReadableDateTime deadline = DateTime.parse(c.getString(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_ASSIGNMENT_DEADLINE)));
        ReadableDuration requiredDuration = new Duration(c.getLong(c.getColumnIndex(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_ASSIGNMENT_REQUIRED_DURATION)));
        return new Assignment(true, uuid, title, deadline, requiredDuration, importance);
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = super.toContentValues();
        contentValues.put(ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IS_EVENT, false);
        contentValues.put(
        ThreadsDatabaseContract.TasksTable.COLUMN_NAME_ASSIGNMENT_DEADLINE,
        mDeadline.toString());
        contentValues.put(
                ThreadsDatabaseContract.TasksTable.COLUMN_NAME_ASSIGNMENT_REQUIRED_DURATION,
                mRequiredDuration.getMillis());
        return contentValues;
    }

    public ReadableDateTime getDeadline() {
        return mDeadline;
    }

    public void setDeadline(ReadableDateTime deadline) {
        acquireWriteLock(true);
        setSaved(false);
        mDeadline = deadline;
        releaseWriteLock();
    }

    public ReadableDuration getRequiredDuration() {
        return mRequiredDuration;
    }

    public void setRequiredDuration(ReadableDuration requiredDuration) {
        acquireWriteLock(true);
        setSaved(false);
        mRequiredDuration = requiredDuration;
        releaseWriteLock();
    }
}
