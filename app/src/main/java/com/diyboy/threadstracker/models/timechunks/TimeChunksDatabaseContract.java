package com.diyboy.threadstracker.models.timechunks;


import android.provider.BaseColumns;

import com.diyboy.threadstracker.models.BaseDatabaseContract;
import com.diyboy.threadstracker.models.threads.ThreadsDatabaseContract;

public class TimeChunksDatabaseContract extends BaseDatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    protected TimeChunksDatabaseContract() {
    }

    public static abstract class TimeChunksTable implements BaseColumns {
        public static final String TABLE_NAME = "time_chunks";
        public static final String COLUMN_NAME_INTERVAL_START = "interval_start";
        public static final String COLUMN_NAME_INTERVAL_END = "interval_end";
        public static final String COLUMN_NAME_ASSIGNED_TASK_UUID = "assigned_task_uuid";

        public static final String COMMAND_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_NAME_INTERVAL_START + DATE_TIME_TYPE + COMMA_SEP +
                COLUMN_NAME_INTERVAL_END + DATE_TIME_TYPE + COMMA_SEP +
                COLUMN_NAME_ASSIGNED_TASK_UUID + STRING_TYPE + " NOT NULL" + COMMA_SEP +
                "FOREIGN KEY(" + COLUMN_NAME_ASSIGNED_TASK_UUID + ") REFERENCES " +
                        ThreadsDatabaseContract.TasksTable.TABLE_NAME +"(" +
                        ThreadsDatabaseContract.TasksTable.COLUMN_NAME_UUID + ")" + COMMA_SEP +
                "UNIQUE (" + COLUMN_NAME_INTERVAL_START + COMMA_SEP + COLUMN_NAME_INTERVAL_END + ") " +
                "ON CONFLICT REPLACE" +
                ")";
        public static final String COMMAND_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
