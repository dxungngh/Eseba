package com.eseba.jp.bus.listener;

/**
 * Created by danielnguyen on 8/28/17.
 */

public interface OnSettingClickListener {
    void notificationOnClick();

    void areaGroupListOnClick();

    void areaGroupOnClick(String areaGroupName);

    void genreGroupListOnClick();

    void genreGroupOnClick(String genreGroupName);

    void onBackFragmentPressed();
}
