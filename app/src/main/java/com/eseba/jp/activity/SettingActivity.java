package com.eseba.jp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.business.ConfigurationBusiness;
import com.eseba.jp.database.table.Configuration;
import com.eseba.jp.fragment.setting.SettingAllFragment;
import com.eseba.jp.fragment.setting.SettingAreaGroupListFragment;
import com.eseba.jp.fragment.setting.SettingAreaListFragment;
import com.eseba.jp.fragment.setting.SettingGenreGroupListFragment;
import com.eseba.jp.fragment.setting.SettingGenreListFragment;
import com.eseba.jp.fragment.setting.SettingNotificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements OnSettingClickListener {
    private static final String TAG = SettingActivity.class.getSimpleName();

    @BindView(R.id.setting_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        super.setSupportActionBar(this.toolbar);
        this.drawComponentViews();
        this.checkConfiguration();
    }

    @OnClick(R.id.toolbar_setting)
    public void settingButtonOnClick() {
        super.getSupportFragmentManager()
            .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        this.drawComponentViews();
    }

    @Override
    public void notificationOnClick() {
        this.drawContentWithFragment(SettingNotificationFragment.getInstance(this), true);
    }

    @Override
    public void areaGroupListOnClick() {
        this.drawContentWithFragment(SettingAreaGroupListFragment.getInstance(this), true);
    }

    @Override
    public void areaGroupOnClick(String areaGroupName) {
        this.drawContentWithFragment(SettingAreaListFragment.getInstance(this, areaGroupName), true);
    }

    @Override
    public void genreGroupListOnClick() {
        this.drawContentWithFragment(SettingGenreGroupListFragment.getInstance(this), true);
    }

    @Override
    public void genreGroupOnClick(String genreGroupName) {
        this.drawContentWithFragment(SettingGenreListFragment.getInstance(this, genreGroupName), true);
    }

    @Override
    public void onBackFragmentPressed() {
        super.getSupportFragmentManager().popBackStack();
    }

    private void drawComponentViews() {
        this.drawContentWithFragment(SettingAllFragment.getInstance(this), false);
    }

    private void drawContentWithFragment(Fragment fragment, boolean isAddedToBackStack) {
        if (isAddedToBackStack) {
            super.getSupportFragmentManager()
                .beginTransaction()
                .replace(
                    R.id.setting_content,
                    fragment
                )
                .addToBackStack(null)
                .commit();
        } else {
            super.getSupportFragmentManager()
                .beginTransaction()
                .replace(
                    R.id.setting_content,
                    fragment
                )
                .commit();
        }
    }

    private void checkConfiguration() {
        ConfigurationBusiness business = (ConfigurationBusiness) ServiceRegistry.getService(ConfigurationBusiness.TAG);
        Configuration configuration = business.getConfiguration();
        if (configuration == null) {
            configuration = new Configuration();
            business.createConfiguration(configuration);
        }
    }
}
