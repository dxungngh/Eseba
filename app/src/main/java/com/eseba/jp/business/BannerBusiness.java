package com.eseba.jp.business;

import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.datasource.BannerDataSource;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.listener.OnGetBannerListListener;
import com.eseba.jp.network.BannerNetwork;

import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class BannerBusiness {
    public static final String TAG = BannerBusiness.class.getSimpleName();

    private BannerNetwork network;
    private BannerDataSource dataSource;

    public BannerBusiness() {
        this.network = (BannerNetwork) ServiceRegistry.getService(BannerNetwork.TAG);
        this.dataSource = (BannerDataSource) ServiceRegistry.getService(BannerDataSource.TAG);
    }

    public void getBannerList(final OnGetBannerListListener listener) {
        this.network.getBannerList(new OnGetBannerListListener() {
            @Override
            public void onSuccess(List<Banner> result) {
                if (result != null && result.size() > 0) {
                    dataSource.clearTable();
                    for (Banner banner : result) {
                        dataSource.createBanner(banner);
                    }
                    listener.onSuccess(result);
                }
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }

    public List<Banner> getBannerListFromDatabase() {
        return this.dataSource.getAllBanners();
    }
}
