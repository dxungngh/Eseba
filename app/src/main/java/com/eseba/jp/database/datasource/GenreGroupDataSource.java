package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.GenreGroup;

public class GenreGroupDataSource {
    public static final String TAG = GenreGroupDataSource.class.getSimpleName();

    private Context context;

    public GenreGroupDataSource(Context context) {
        this.context = context;
    }

    public void createGenreGroup(GenreGroup genreGroup) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getGenreGroupDao().create(genreGroup);
        } catch (Exception e) {
            Log.e(TAG, "createGenreGroup", e);
        }
    }
}