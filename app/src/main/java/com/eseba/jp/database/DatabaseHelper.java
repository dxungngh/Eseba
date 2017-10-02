package com.eseba.jp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eseba.jp.Config;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.database.table.Configuration;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.GenreGroup;
import com.eseba.jp.database.table.News;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.Method;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private Dao<Area, Integer> areaDao = null;
    private Dao<Banner, Integer> bannerDao = null;
    private Dao<Genre, Integer> genreDao = null;
    private Dao<GenreGroup, Integer> genreGroupDao = null;
    private Dao<News, Integer> newsDao = null;
    private Dao<Configuration, Integer> configurationDao = null;

    public DatabaseHelper(Context context) {
        super(context, Config.Database.DATABASE_NAME, null, Config.Database.DATABASE_VERSION);
    }

    @Override
    public void close() {
        super.close();
        this.areaDao = null;
        this.bannerDao = null;
        this.genreDao = null;
        this.genreGroupDao = null;
        this.newsDao = null;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(TAG, "create database");
            TableUtils.createTable(connectionSource, Area.class);
            TableUtils.createTable(connectionSource, Banner.class);
            TableUtils.createTable(connectionSource, Genre.class);
            TableUtils.createTable(connectionSource, GenreGroup.class);
            TableUtils.createTable(connectionSource, News.class);
            TableUtils.createTable(connectionSource, Configuration.class);
        } catch (SQLException e) {
            Log.e(TAG, "onCreate", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            Log.e(TAG, "onCreate", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        try {
            Log.i(TAG, "upgrade: " + oldVersion + "--" + newVersion);
            for (int i = oldVersion; i < newVersion; i++) {
                String methodName = "updateFromDatabaseVersion" + i;
                Method method = getClass().getDeclaredMethod(methodName, (Class<?>[]) null);
                method.setAccessible(true);
                method.invoke(this, (Class<?>[]) null);
            }
        } catch (Exception e) {
            Log.e(TAG, "onUpgrade", e);
        }
    }

    public Dao<Area, Integer> getAreaDao() {
        if (null == this.areaDao) {
            try {
                this.areaDao = super.getDao(Area.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getAreaDao", e);
            }
        }
        return this.areaDao;
    }

    public Dao<Banner, Integer> getBannerDao() {
        if (null == this.bannerDao) {
            try {
                this.bannerDao = super.getDao(Banner.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getBannerDao", e);
            }
        }
        return this.bannerDao;
    }

    public Dao<Genre, Integer> getGenreDao() {
        if (null == this.genreDao) {
            try {
                this.genreDao = super.getDao(Genre.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getGenreDao", e);
            }
        }
        return this.genreDao;
    }

    public Dao<GenreGroup, Integer> getGenreGroupDao() {
        if (null == this.genreGroupDao) {
            try {
                this.genreGroupDao = super.getDao(GenreGroup.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getGenreGroupDao", e);
            }
        }
        return this.genreGroupDao;
    }

    public Dao<News, Integer> getNewsDao() {
        if (null == this.newsDao) {
            try {
                this.newsDao = super.getDao(News.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getNewsDao", e);
            }
        }
        return this.newsDao;
    }

    public Dao<Configuration, Integer> getConfigurationDao() {
        if (null == this.configurationDao) {
            try {
                this.configurationDao = super.getDao(Configuration.class);
            } catch (java.sql.SQLException e) {
                Log.e(TAG, "getConfigurationDao", e);
            }
        }
        return this.configurationDao;
    }

    public void clearAreaTable() {
        try {
            TableUtils.clearTable(this.connectionSource, Area.class);
        } catch (Exception e) {
            Log.e(TAG, "clearDatabase", e);
        }
    }

    public void clearBannerTable() {
        try {
            TableUtils.clearTable(this.connectionSource, Banner.class);
        } catch (Exception e) {
            Log.e(TAG, "clearDatabase", e);
        }
    }

    public void clearNewsTable() {
        try {
            TableUtils.clearTable(this.connectionSource, News.class);
        } catch (Exception e) {
            Log.e(TAG, "clearNewsTable", e);
        }
    }
}

