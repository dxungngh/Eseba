package com.eseba.jp.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.adapter.ItemGroupAdapter;
import com.eseba.jp.bus.listener.OnItemGroupClickListener;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.business.GenreBusiness;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 9/30/17.
 */

public class SettingGenreGroupListFragment extends Fragment implements OnItemGroupClickListener {
    private static final String TAG = SettingGenreGroupListFragment.class.getSimpleName();
    private static SettingGenreGroupListFragment instance;

    @BindView(R.id.fragment_setting_item_group_list)
    RecyclerView areaGroupRecyclerView;

    private OnSettingClickListener listener;
    private List<String> genreGroupList;

    private GenreBusiness business;

    public static SettingGenreGroupListFragment getInstance(OnSettingClickListener listener) {
        if (instance == null) {
            instance = new SettingGenreGroupListFragment();
        }
        instance.listener = listener;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_item_group, container, false);
        ButterKnife.bind(this, view);
        this.initData();
        this.drawComponentViews();
        return view;
    }

    @OnClick(R.id.fragment_setting_item_group_back)
    public void backOnClick() {
        this.listener.onBackFragmentPressed();
    }

    @Override
    public void onItemGroupClick(String itemGroup) {
        this.listener.genreGroupOnClick(itemGroup);
    }

    private void initData() {
        this.business = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
        this.genreGroupList = this.business.getGenreGroupListOnDatabase();
    }

    private void drawComponentViews() {
        this.setupRecyclerView();
        this.drawAreaGroupList();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.areaGroupRecyclerView.setLayoutManager(layoutManager);
        this.areaGroupRecyclerView.setHasFixedSize(false);
    }

    private void drawAreaGroupList() {
        ItemGroupAdapter adapter = new ItemGroupAdapter(this.genreGroupList, this);
        this.areaGroupRecyclerView.setAdapter(adapter);
    }
}
