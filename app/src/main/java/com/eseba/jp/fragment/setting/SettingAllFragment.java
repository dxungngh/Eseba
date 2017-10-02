package com.eseba.jp.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class SettingAllFragment extends Fragment {
    private static final String TAG = SettingAllFragment.class.getSimpleName();
    private static SettingAllFragment instance;

    private OnSettingClickListener listener;

    public static SettingAllFragment getInstance(OnSettingClickListener listener) {
        if (instance == null) {
            instance = new SettingAllFragment();
        }
        instance.listener = listener;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_all, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_setting_notification)
    public void notificationOnClick() {
        this.listener.notificationOnClick();
    }

    @OnClick(R.id.fragment_setting_area)
    public void areaGroupOnClick() {
        this.listener.areaGroupListOnClick();
    }

    @OnClick(R.id.fragment_setting_gender)
    public void genreGroupOnClick() {
        this.listener.genreGroupListOnClick();
    }
}
