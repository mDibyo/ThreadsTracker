package com.diyboy.threadstracker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.diyboy.threadstracker.models.threads.Assignment;
import com.diyboy.threadstracker.models.threads.Event;
import com.diyboy.threadstracker.models.threads.Task;
import com.diyboy.threadstracker.models.threads.Thread;
import com.diyboy.threadstracker.models.threads.ThreadsDatabaseContract;
import com.diyboy.threadstracker.models.timechunks.TimeChunk;
import com.diyboy.threadstracker.models.timechunks.TimeChunksDatabaseContract;

import org.joda.time.ReadableDateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ThreadsTrackerState {
    public static String THREADS_TRACKER_STATE_LOG_TAG = "ThreadsTrackerState";

    private static ThreadsTrackerState instance;

    private Map<UUID, Thread> mThreadMap;
    private Map<ReadableDateTime, TimeChunk> mTimeChunkMap;

    public static ThreadsTrackerState getInstance(Context context) {
        if (instance == null) {
            SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();
            instance = ThreadsTrackerState.fromDatabase(db);
        }
        return instance;
    }

    private ThreadsTrackerState(Map<UUID, Thread> threadMap,
                               Map<ReadableDateTime, TimeChunk> timeChunkMap) {
        mThreadMap = threadMap;
        mTimeChunkMap = timeChunkMap;
    }

    private static ThreadsTrackerState fromDatabase(SQLiteDatabase db) {
        Map<UUID, Thread> threadMap = new HashMap<>();
        Cursor threadsCursor = db.query(
                ThreadsDatabaseContract.ThreadsTable.TABLE_NAME, null, null, null, null, null, null);
        threadsCursor.moveToFirst();
        while (!threadsCursor.isAfterLast()) {
            Thread thread = Thread.fromDatabaseCursor(threadsCursor);
            threadMap.put(thread.getUuid(), thread);
            threadsCursor.moveToNext();
        }
        threadsCursor.close();

        Map<UUID, Task> taskMap = new HashMap<>();
        Cursor tasksCursor = db.query(
                ThreadsDatabaseContract.TasksTable.TABLE_NAME, null, null, null, null, null, null);
        tasksCursor.moveToFirst();
        Task task;
        Thread thread;
        while (!tasksCursor.isAfterLast()) {
            boolean isEvent =
                    tasksCursor.getInt(tasksCursor.getColumnIndex(
                            ThreadsDatabaseContract.TasksTable.COLUMN_NAME_IS_EVENT)) != 0;
            if (isEvent) {
                task = Event.fromDatabaseCursor(tasksCursor);
            } else {
                task = Assignment.fromDatabaseCursor(tasksCursor);
            }
            thread = threadMap.get(UUID.fromString(tasksCursor.getString(tasksCursor.getColumnIndex(
                            ThreadsDatabaseContract.TasksTable.COLUMN_NAME_THREAD_UUID)
            )));
            task.setThread(thread);
            taskMap.put(task.getUuid(), task);
            threadMap.get(thread.getUuid()).getTasks().add(task);
            tasksCursor.moveToNext();
        }

        Map<ReadableDateTime, TimeChunk> timeChunkMap = new HashMap<>();
        Cursor timeChunksCursor = db.query(
                TimeChunksDatabaseContract.TimeChunksTable.TABLE_NAME,
                null, null, null, null, null, null);
        timeChunksCursor.moveToFirst();
        TimeChunk timeChunk;
        while (!timeChunksCursor.isAfterLast()) {
            timeChunk = TimeChunk.fromDatabaseCursor(timeChunksCursor);
            timeChunkMap.put(timeChunk.getInterval().getStart(), timeChunk);
            String taskUuidString = timeChunksCursor.getString(timeChunksCursor.getColumnIndex(
                    TimeChunksDatabaseContract.TimeChunksTable.COLUMN_NAME_ASSIGNED_TASK_UUID
            ));
            if (taskUuidString != null) {
                task = taskMap.get(UUID.fromString(taskUuidString));
                timeChunk.assignTask(task);
                task.getAllocatedTimeChunks().add(timeChunk);
            }
            timeChunksCursor.moveToNext();
        }

        return new ThreadsTrackerState(threadMap, timeChunkMap);
    }

    public synchronized boolean flushToDatabase(Context context) {
        try {
            SQLiteDatabase db = DatabaseHelper.getInstance(context).getWritableDatabase();

            for (Thread thread : mThreadMap.values()) {
                syncWithDatabase(db, ThreadsDatabaseContract.ThreadsTable.TABLE_NAME, thread);

                for (Task task : thread.getTasksReadOnly()) {
                    syncWithDatabase(db, ThreadsDatabaseContract.TasksTable.TABLE_NAME, task);
                }
            }

            for (TimeChunk timeChunk : mTimeChunkMap.values()) {
                syncWithDatabase(db, TimeChunksDatabaseContract.TimeChunksTable.TABLE_NAME, timeChunk);
            }

            return true;
        } catch (SQLiteException exception) {
            Log.e(THREADS_TRACKER_STATE_LOG_TAG, "flushToDatabase() failed", exception);
            return false;
        }
    }

    private void syncWithDatabase(SQLiteDatabase db, String tableName, DatabaseSyncable ds)
            throws SQLiteException {
        ds.acquireWriteLock(true);
        if (!ds.isSaved()) {
            ContentValues contentValues = ds.toContentValues();
            db.insertWithOnConflict(tableName, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
            ds.setSaved(true);
        }
        ds.releaseWriteLock();
    }

    public Map<UUID, Thread> getThreadMap() {
        return mThreadMap;
    }

    public Map<ReadableDateTime, TimeChunk> getTimeChunkMap() {
        return mTimeChunkMap;
    }
}
