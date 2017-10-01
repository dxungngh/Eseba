package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseHelper;
import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.Area;

import java.util.List;

public class AreaDataSource {
    public static final String TAG = AreaDataSource.class.getSimpleName();

    private Context context;

    public AreaDataSource(Context context) {
        this.context = context;
    }

    public void clearTable() {
        DatabaseManager.getInstance(this.context).getHelper().clearAreaTable();
    }

    public List<Area> getAllAreas() {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(this.context).getHelper();
            List<Area> areas = helper.getAreaDao().queryForAll();
            return areas;
        } catch (Exception e) {
            Log.e(TAG, "getAllAreas", e);
            return null;
        }
    }

    public List<Area> getActiveAreas() {
        try {
            return DatabaseManager.getInstance(context).getHelper().getAreaDao()
                .queryForEq(Area.Fields.IS_ACTIVE, true);
        } catch (Exception e) {
            Log.e(TAG, "getActiveArea", e);
            return null;
        }
    }

    public void createArea(Area area) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getAreaDao().create(area);
        } catch (Exception e) {
            Log.e(TAG, "createArea", e);
        }
    }

    public void updateArea(long id, Area area) {
        try {
            area.setId(id);
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            helper.getAreaDao().update(area);
        } catch (Exception e) {
            Log.e(TAG, "updateArea", e);
        }
    }
}