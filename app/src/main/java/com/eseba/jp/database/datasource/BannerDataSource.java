package com.eseba.jp.database.datasource;

import android.content.Context;
import android.util.Log;

import com.eseba.jp.database.DatabaseHelper;
import com.eseba.jp.database.DatabaseManager;
import com.eseba.jp.database.table.Banner;

import java.util.List;

public class BannerDataSource {
    public static final String TAG = BannerDataSource.class.getSimpleName();

    private Context context;

    public BannerDataSource(Context context) {
        this.context = context;
    }

    public void clearTable() {
        DatabaseManager.getInstance(this.context).getHelper().clearBannerTable();
    }

    public void createBanner(Banner banner) {
        try {
            DatabaseManager.getInstance(this.context).getHelper().getBannerDao().create(banner);
        } catch (Exception e) {
            Log.e(TAG, "createBanner", e);
        }
    }

    public List<Banner> getAllBanners() {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(this.context).getHelper();
            List<Banner> banners = helper.getBannerDao().queryForAll();
            return banners;
        } catch (Exception e) {
            Log.e(TAG, "getAllBanners", e);
            return null;
        }
    }
}