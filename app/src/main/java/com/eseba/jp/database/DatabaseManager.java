package com.eseba.jp.database;

import android.content.Context;

/**
 * Created by daniel_nguyen on 6/15/14.
 */
public class DatabaseManager {
    private DatabaseHelper helper;
    private static DatabaseManager instance;

    private static final String TAG = DatabaseManager.class.getSimpleName();

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public DatabaseHelper getHelper() {
        return this.helper;
    }
}