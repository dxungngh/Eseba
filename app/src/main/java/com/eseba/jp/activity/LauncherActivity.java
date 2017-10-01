package com.eseba.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.business.AreaBusiness;
import com.eseba.jp.business.BannerBusiness;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.business.NewsBusiness;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.database.table.GenreGroup;
import com.eseba.jp.database.table.News;
import com.eseba.jp.listener.OnGetAreaListListener;
import com.eseba.jp.listener.OnGetBannerListListener;
import com.eseba.jp.listener.OnGetGenreGroupListListener;
import com.eseba.jp.listener.OnGetNewsListListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = LauncherActivity.class.getSimpleName();

    @BindView(R.id.launcher_progress_bar)
    protected ProgressBar launcherProgressBar;

    private AreaBusiness areaBusiness;
    private GenreBusiness genreBusiness;
    private BannerBusiness bannerBusiness;
    private NewsBusiness newsBusiness;

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        this.initService();
        this.getData();
    }

    private void initService() {
        this.areaBusiness = (AreaBusiness) ServiceRegistry.getService(AreaBusiness.TAG);
        this.bannerBusiness = (BannerBusiness) ServiceRegistry.getService(BannerBusiness.TAG);
        this.genreBusiness = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
        this.newsBusiness = (NewsBusiness) ServiceRegistry.getService(NewsBusiness.TAG);
    }

    private void getData() {
        this.getAreaList();
        this.getGenreGroupList();
        this.getBannerList();
        this.getNewsList();
    }

    private void getAreaList() {
        this.areaBusiness.getAreaList(new OnGetAreaListListener() {
            @Override
            public void onSuccess(List<Area> result) {
                progress += 20;
                updateProgressBar();
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getAreaList", error);
            }
        });
    }

    private void getGenreGroupList() {
        this.genreBusiness.getGenreGroupList(new OnGetGenreGroupListListener() {
            @Override
            public void onSuccess(List<GenreGroup> result) {
                progress += 20;
                updateProgressBar();
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getGenreGroupList", error);
            }
        });
    }

    private void getBannerList() {
        this.bannerBusiness.getBannerList(new OnGetBannerListListener() {
            @Override
            public void onSuccess(List<Banner> result) {
                progress += 20;
                updateProgressBar();
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getBannerList", error);
            }
        });
    }

    private void getNewsList() {
        this.newsBusiness.getNewsList(new OnGetNewsListListener() {
            @Override
            public void onSuccess(List<News> result) {
                progress += 40;
                updateProgressBar();
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getNewsList", error);
            }
        });
    }

    private void updateProgressBar() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                launcherProgressBar.setProgress(progress);
                if (progress >= 40) {
                    openMainScreen();
                }
            }
        });
    }

    private void openMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        super.startActivity(intent);
        super.finish();
    }
}
