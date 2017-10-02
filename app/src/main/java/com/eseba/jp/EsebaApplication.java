package com.eseba.jp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.eseba.jp.business.AreaBusiness;
import com.eseba.jp.business.BannerBusiness;
import com.eseba.jp.business.ConfigurationBusiness;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.business.ImageBusiness;
import com.eseba.jp.business.NewsBusiness;
import com.eseba.jp.database.datasource.AreaDataSource;
import com.eseba.jp.database.datasource.BannerDataSource;
import com.eseba.jp.database.datasource.ConfigurationDataSource;
import com.eseba.jp.database.datasource.GenreDataSource;
import com.eseba.jp.database.datasource.GenreGroupDataSource;
import com.eseba.jp.database.datasource.NewsDataSource;
import com.eseba.jp.network.AreaNetwork;
import com.eseba.jp.network.BannerNetwork;
import com.eseba.jp.network.GenreNetwork;
import com.eseba.jp.network.NewsNetwork;
import com.eseba.jp.network.parser.AreaParser;
import com.eseba.jp.network.parser.BannerParser;
import com.eseba.jp.network.parser.GenreParser;
import com.eseba.jp.network.parser.NewsParser;
import com.eseba.jp.network.volley.MyVolley;

import io.fabric.sdk.android.Fabric;

/**
 * Created by danielnguyen on 8/21/17.
 */

public class EsebaApplication extends Application {
    private static final String TAG = EsebaApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        this.registerServices();
    }

    private void registerServices() {
        this.initApplicationInfo();
        this.registerAPI();
        this.registerParsers();
        this.registerNetworks();
        this.registerDataSources();
        this.registerBusinesses();
    }

    private void initApplicationInfo() {
    }

    private void registerAPI() {
        ServiceRegistry.registerService(MyVolley.TAG, new MyVolley(super.getApplicationContext()));
    }

    private void registerBusinesses() {
        ServiceRegistry.registerService(ImageBusiness.TAG, new ImageBusiness(super.getApplicationContext()));
        ServiceRegistry.registerService(AreaBusiness.TAG, new AreaBusiness());
        ServiceRegistry.registerService(BannerBusiness.TAG, new BannerBusiness());
        ServiceRegistry.registerService(GenreBusiness.TAG, new GenreBusiness());
        ServiceRegistry.registerService(NewsBusiness.TAG, new NewsBusiness());
        ServiceRegistry.registerService(ConfigurationBusiness.TAG, new ConfigurationBusiness());
    }

    private void registerDataSources() {
        ServiceRegistry.registerService(AreaDataSource.TAG, new AreaDataSource(super.getApplicationContext()));
        ServiceRegistry.registerService(BannerDataSource.TAG, new BannerDataSource(super.getApplicationContext()));
        ServiceRegistry.registerService(GenreDataSource.TAG, new GenreDataSource(super.getApplicationContext()));
        ServiceRegistry.registerService(GenreGroupDataSource.TAG, new GenreGroupDataSource(super.getApplicationContext()));
        ServiceRegistry.registerService(NewsDataSource.TAG, new NewsDataSource(super.getApplicationContext()));
        ServiceRegistry.registerService(ConfigurationDataSource.TAG, new ConfigurationDataSource(super.getApplicationContext()));
    }

    private void registerNetworks() {
        ServiceRegistry.registerService(AreaNetwork.TAG, new AreaNetwork());
        ServiceRegistry.registerService(BannerNetwork.TAG, new BannerNetwork());
        ServiceRegistry.registerService(GenreNetwork.TAG, new GenreNetwork());
        ServiceRegistry.registerService(NewsNetwork.TAG, new NewsNetwork());
    }

    private void registerParsers() {
        ServiceRegistry.registerService(AreaParser.TAG, new AreaParser());
        ServiceRegistry.registerService(BannerParser.TAG, new BannerParser());
        ServiceRegistry.registerService(GenreParser.TAG, new GenreParser());
        ServiceRegistry.registerService(NewsParser.TAG, new NewsParser());
    }
}
