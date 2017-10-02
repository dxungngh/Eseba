package com.eseba.jp.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eseba.jp.Config;
import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.adapter.AreaAdapter;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.business.AreaBusiness;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 9/30/17.
 */

public class SettingAreaListFragment extends Fragment {
    private static final String TAG = SettingAreaListFragment.class.getSimpleName();
    private static SettingAreaListFragment instance;

    @BindView(R.id.fragment_setting_item_list_group_name_title)
    protected MyTextView groupNameTextView;
    @BindView(R.id.fragment_setting_item_list_recycler_view)
    protected RecyclerView areaListRecyclerView;

    private OnSettingClickListener listener;
    private List<Area> areaList;
    private String areaGroupName;

    private AreaBusiness business;

    public static SettingAreaListFragment getInstance(OnSettingClickListener listener,
                                                      String areaGroupName) {
        if (instance == null) {
            instance = new SettingAreaListFragment();
        }
        instance.listener = listener;
        Bundle args = new Bundle();
        args.putString(Config.Extras.AREA_GROUP_NAME, areaGroupName);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.areaGroupName = super.getArguments().getString(Config.Extras.AREA_GROUP_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_item_list, container, false);
        ButterKnife.bind(this, view);
        this.initData();
        this.drawComponentViews();
        return view;
    }

    @OnClick(R.id.fragment_setting_item_list_back)
    public void backOnClick() {
        this.listener.onBackFragmentPressed();
    }

    private void initData() {
        this.business = (AreaBusiness) ServiceRegistry.getService(AreaBusiness.TAG);
        this.areaList = this.business.getAreaListOfGroup(this.areaGroupName);
    }

    private void drawComponentViews() {
        this.groupNameTextView.setText(this.areaGroupName);
        this.setupRecyclerView();
        this.drawAreaList();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.areaListRecyclerView.setLayoutManager(layoutManager);
        this.areaListRecyclerView.setHasFixedSize(false);
    }

    private void drawAreaList() {
        AreaAdapter adapter = new AreaAdapter(this.areaList);
        this.areaListRecyclerView.setAdapter(adapter);
    }
}
