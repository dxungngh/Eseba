package com.eseba.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.eseba.jp.Config;
import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.adapter.InfiniteTabPagerAdapter;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.fragment.tab.AllTabFragment;
import com.eseba.jp.fragment.tab.BaseTabFragment;
import com.eseba.jp.fragment.tab.NormalTabFragment;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.main_tab_layout)
    protected RecyclerTabLayout tabLayout;
    @BindView(R.id.main_news_type_viewpager)
    protected ViewPager newsTypeViewPager;

    private InfiniteTabPagerAdapter tabsAdapter;
    private List<Fragment> fragmentList;

    private GenreBusiness genreBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.setSupportActionBar(this.toolbar);
        this.initData();
        this.drawNewsTypeViewPager();
    }

    @Override
    public void onBackPressed() {
        int currentTab = this.newsTypeViewPager.getCurrentItem() % this.fragmentList.size();
        Fragment currentFragment = this.fragmentList.get(currentTab);
        if (((BaseTabFragment) currentFragment).onBackPressed()) {
            super.finish();
        }
    }

    @OnClick(R.id.toolbar_setting)
    public void settingButtonOnClick() {
        Intent intent = new Intent(this, SettingActivity.class);
        super.startActivity(intent);
    }

    private void initData() {
        this.genreBusiness = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
    }

    private void drawNewsTypeViewPager() {
        List<Genre> genreList = this.genreBusiness.getActiveGenres();
        this.fragmentList = new ArrayList<>();
        this.fragmentList.add(AllTabFragment.newInstance(0, super.getString(R.string.news_type_3)));
        for (int i = 0; i < genreList.size(); i++) {
            this.fragmentList.add(NormalTabFragment.newInstance(i + 1, genreList.get(i)));
        }
        this.tabsAdapter = new InfiniteTabPagerAdapter(super.getSupportFragmentManager());
        this.tabsAdapter.addAll(
            this.fragmentList,
            super.getString(R.string.news_type_3),
            genreList
        );
        this.newsTypeViewPager.setAdapter(this.tabsAdapter);
        this.newsTypeViewPager.setCurrentItem(this.getAllTabPosition(genreList.size() + 1));
        this.tabLayout.setUpWithViewPager(this.newsTypeViewPager);
    }

    private int getAllTabPosition(int normalTabsSize) {
        return (Config.Tab.NUMBER_OF_LOOPS / normalTabsSize / 2) * normalTabsSize;
    }
}
