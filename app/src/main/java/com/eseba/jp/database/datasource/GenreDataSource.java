package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.Genre;

import java.util.List;

public class GenreDataSource {
    public static final String TAG = GenreDataSource.class.getSimpleName();

    private Context context;

    public GenreDataSource(Context context) {
        this.context = context;
    }

    public void createGenre(Genre genre) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getGenreDao().create(genre);
        } catch (Exception e) {
            Log.e(TAG, "createGenre", e);
        }
    }

    public void deleteGenre(Genre genre) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getGenreDao().delete(genre);
        } catch (Exception e) {
            Log.e(TAG, "deleteGenre", e);
        }
    }

    public List<Genre> getActiveGenres() {
        try {
            return DatabaseManager.getInstance(context).getHelper().getGenreDao()
                .queryForEq(Genre.Fields.IS_ACTIVE, true);
        } catch (Exception e) {
            Log.e(TAG, "getActiveGenres", e);
            return null;
        }
    }

    public List<Genre> getAllGenres() {
        try {
            return DatabaseManager.getInstance(context).getHelper().getGenreDao().queryForAll();
        } catch (Exception e) {
            Log.e(TAG, "getAllGenres", e);
            return null;
        }
    }

    public void saveGenre(Genre genre) {
        try {
            DatabaseManager.getInstance(context).getHelper().getGenreDao().update(genre);
        } catch (Exception e) {
            Log.e(TAG, "saveGenre", e);
        }
    }
}