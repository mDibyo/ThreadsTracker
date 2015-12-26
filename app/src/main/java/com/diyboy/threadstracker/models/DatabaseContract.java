package com.diyboy.threadstracker.models;


import android.provider.BaseColumns;

public class DatabaseContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    private static final String STRING_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String BOOLEAN_TYPE = " BOOLEAN";
    private static final String DATE_TIME_TYPE = " DATETIME";
    private static final String DURATION_TYPE = " REAL";
    private static final String COMMA_SEP = ", ";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {}

    public static abstract class ThreadsTable implements BaseColumns {
        public static final String TABLE_NAME = "threads";
        public static final String COLUMN_NAME_UUID = "uuid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMPORTANCE = "importance";

        public static final String COMMAND_CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_NAME_UUID + " NOT NULL" + STRING_TYPE + COMMA_SEP +
                COLUMN_NAME_TITLE + " NOT NULL" + STRING_TYPE + COMMA_SEP +
                COLUMN_NAME_IMPORTANCE + " NOT NULL" + FLOAT_TYPE + COMMA_SEP +
                " )";
        public static final String COMMAND_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class TasksTable implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_UUID = "uuid";
        public static final String COLUMN_NAME_THREAD_UUID = "thread_uuid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMPORTANCE = "importance";
        public static final String COLUMN_NAME_DONE = "done";
        public static final String COLUMN_NAME_ASSIGNMENT_DEADLINE = "assignment_deadline";
        public static final String COLUMN_NAME_ASSIGNMENT_REQUIRED_DURATION =
                "assignment_required_duration";
        public static final String COLUMN_NAME_EVENT_INTERVAL_START = "event_interval_start";
        public static final String COLUMN_NAME_EVENT_INTERVAL_END = "event_interval_end";
        
        public static final String COMMAND_CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_NAME_UUID + STRING_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_THREAD_UUID + STRING_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_TITLE + STRING_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_IMPORTANCE + FLOAT_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_DONE + BOOLEAN_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_ASSIGNMENT_DEADLINE + DATE_TIME_TYPE + COMMA_SEP +
                COLUMN_NAME_ASSIGNMENT_REQUIRED_DURATION + DURATION_TYPE + COMMA_SEP +
                COLUMN_NAME_EVENT_INTERVAL_START + DATE_TIME_TYPE + COMMA_SEP +
                COLUMN_NAME_EVENT_INTERVAL_END + DATE_TIME_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + COLUMN_NAME_THREAD_UUID + ") REFERENCES " + ThreadsTable.TABLE_NAME +"(" + ThreadsTable.COLUMN_NAME_UUID + ")" +
                " )";
        public static final String COMMAND_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
