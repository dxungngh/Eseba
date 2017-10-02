package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseHelper;
import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.Configuration;

public class ConfigurationDataSource {
    public static final String TAG = ConfigurationDataSource.class.getSimpleName();

    private Context context;

    public ConfigurationDataSource(Context context) {
        this.context = context;
    }

    public Configuration getConfiguration() {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(this.context).getHelper();
            return helper.getConfigurationDao().queryForFirst(
                helper.getConfigurationDao().queryBuilder().prepare());
        } catch (Exception e) {
            Log.e(TAG, "getConfiguration", e);
            return null;
        }
    }

    public void createConfiguration(Configuration configuration) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(this.context).getHelper();
            helper.getConfigurationDao().create(configuration);
        } catch (Exception e) {
            Log.e(TAG, "createConfiguration", e);
        }
    }

    public void updateConfiguration(Configuration configuration) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(this.context).getHelper();
            helper.getConfigurationDao().update(configuration);
        } catch (Exception e) {
            Log.e(TAG, "updateConfiguration", e);
        }
    }
}