package com.diyboy.threadstracker.models;


import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BaseDatabaseContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "threadsTrackerDatabase.db";

    protected static final String STRING_TYPE = " TEXT";
    protected static final String FLOAT_TYPE = " REAL";
    protected static final String BOOLEAN_TYPE = " BOOLEAN";
    protected static final String DATE_TIME_TYPE = " DATETIME";
    protected static final String DURATION_TYPE = " REAL";
    protected static final String COMMA_SEP = ", ";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    protected BaseDatabaseContract() {
    }
}
