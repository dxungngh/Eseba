package com.eseba.jp.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.business.ConfigurationBusiness;
import com.eseba.jp.database.table.Configuration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 9/30/17.
 */

public class SettingNotificationFragment extends Fragment {
    private static final String TAG = SettingNotificationFragment.class.getSimpleName();
    private static SettingNotificationFragment instance;

    @BindView(R.id.fragment_setting_notification_toggle_button)
    protected ToggleButton isActiveNotificationButton;

    private OnSettingClickListener listener;
    private Configuration configuration;

    private ConfigurationBusiness business;

    public static SettingNotificationFragment getInstance(OnSettingClickListener listener) {
        if (instance == null) {
            instance = new SettingNotificationFragment();
        }
        instance.listener = listener;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_notification, container, false);
        ButterKnife.bind(this, view);
        this.initData();
        this.drawComponentViews();
        return view;
    }

    @OnClick(R.id.fragment_setting_notification_back)
    public void backOnClick() {
        this.listener.onBackFragmentPressed();
    }

    @OnClick(R.id.fragment_setting_notification_toggle_button)
    public void notificationButtonOnClick() {
        this.configuration.setPushNotification(!this.configuration.isPushNotification());
        this.business.updateConfiguration(this.configuration);
    }

    private void initData() {
        this.business = (ConfigurationBusiness) ServiceRegistry.getService(ConfigurationBusiness.TAG);
        this.configuration = this.business.getConfiguration();
    }

    private void drawComponentViews() {
        if (this.configuration != null) {
            this.isActiveNotificationButton.setChecked(this.configuration.isPushNotification());
        }
    }
}
