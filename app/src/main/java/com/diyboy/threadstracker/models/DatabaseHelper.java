package com.diyboy.threadstracker.models;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.diyboy.threadstracker.models.threads.ThreadsDatabaseContract;
import com.diyboy.threadstracker.models.timechunks.TimeChunksDatabaseContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, BaseDatabaseContract.DATABASE_NAME, null,BaseDatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ThreadsDatabaseContract.ThreadsTable.COMMAND_CREATE_TABLE);
        db.execSQL(ThreadsDatabaseContract.TasksTable.COMMAND_CREATE_TABLE);
        db.execSQL(TimeChunksDatabaseContract.TimeChunksTable.COMMAND_CREATE_TABLE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            db.enableWriteAheadLogging();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ThreadsDatabaseContract.ThreadsTable.COMMAND_DELETE_TABLE);
        db.execSQL(ThreadsDatabaseContract.TasksTable.COMMAND_DELETE_TABLE);
        db.execSQL(TimeChunksDatabaseContract.TimeChunksTable.COMMAND_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
