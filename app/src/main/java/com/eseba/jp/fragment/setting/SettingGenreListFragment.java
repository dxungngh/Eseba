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
import com.eseba.jp.adapter.GenreAdapter;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 9/30/17.
 */

public class SettingGenreListFragment extends Fragment {
    private static final String TAG = SettingGenreListFragment.class.getSimpleName();
    private static SettingGenreListFragment instance;

    @BindView(R.id.fragment_setting_item_list_group_name_title)
    protected MyTextView groupNameTextView;
    @BindView(R.id.fragment_setting_item_list_recycler_view)
    protected RecyclerView genreListRecyclerView;

    private OnSettingClickListener listener;
    private List<Genre> genreList;
    private String genreGroupName;

    private GenreBusiness business;

    public static SettingGenreListFragment getInstance(OnSettingClickListener listener,
                                                       String areaGroupName) {
        if (instance == null) {
            instance = new SettingGenreListFragment();
        }
        instance.listener = listener;
        Bundle args = new Bundle();
        args.putString(Config.Extras.GENRE_GROUP_NAME, areaGroupName);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.genreGroupName = super.getArguments().getString(Config.Extras.GENRE_GROUP_NAME);
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
        this.business = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
        this.genreList = this.business.getGenreListOfGroup(this.genreGroupName);
    }

    private void drawComponentViews() {
        if (this.genreGroupName.equals("event")) {
            this.groupNameTextView.setText("ジャンル設定：イベント");
        } else if (this.genreGroupName.equals("seminar")) {
            this.groupNameTextView.setText("ジャンル設定：セミナー");
        } else this.groupNameTextView.setText("ジャンル設定：" + this.genreGroupName);
        this.setupRecyclerView();
        this.drawAreaList();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.genreListRecyclerView.setLayoutManager(layoutManager);
        this.genreListRecyclerView.setHasFixedSize(false);
    }

    private void drawAreaList() {
        GenreAdapter adapter = new GenreAdapter(this.genreList);
        this.genreListRecyclerView.setAdapter(adapter);
    }
}
