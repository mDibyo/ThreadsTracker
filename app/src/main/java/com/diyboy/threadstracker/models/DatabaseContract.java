package com.diyboy.threadstracker.models;


import android.provider.BaseColumns;

public class DatabaseContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    private static final String STRING_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

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
                COLUMN_NAME_UUID + STRING_TYPE + COMMA_SEP +
                COLUMN_NAME_TITLE + STRING_TYPE + COMMA_SEP +
                COLUMN_NAME_IMPORTANCE + FLOAT_TYPE + COMMA_SEP + " )";
        public static final String COMMAND_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
