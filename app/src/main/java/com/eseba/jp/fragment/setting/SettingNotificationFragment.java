package com.eseba.jp.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eseba.jp.R;
import com.eseba.jp.bus.listener.OnSettingClickListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 9/30/17.
 */

public class SettingNotificationFragment extends Fragment {
    private static final String TAG = SettingNotificationFragment.class.getSimpleName();
    private static SettingNotificationFragment instance;

    private OnSettingClickListener listener;

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
        return view;
    }

    @OnClick(R.id.fragment_setting_notification_back)
    public void backOnClick() {
        Log.i(TAG, "backOnClick");
        this.listener.onBackFragmentPressed();
    }
}
