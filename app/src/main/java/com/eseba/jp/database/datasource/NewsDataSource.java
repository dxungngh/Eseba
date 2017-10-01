package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.News;

import java.util.List;

public class NewsDataSource {
    public static final String TAG = NewsDataSource.class.getSimpleName();

    private Context context;

    public NewsDataSource(Context context) {
        this.context = context;
    }

    public void clearTable() {
        DatabaseManager.getInstance(this.context).getHelper().clearNewsTable();
    }

    public void createNews(News news) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getNewsDao().create(news);
        } catch (Exception e) {
            Log.e(TAG, "createNews", e);
        }
    }

    public List<News> getNewsOfAllTab() {
        try {
            return DatabaseManager.getInstance(this.context).getHelper().getNewsDao().queryForAll();
        } catch (Exception e) {
            Log.e(TAG, "getNewsOfAllTab", e);
        }
        return null;
    }
}