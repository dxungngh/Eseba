package com.eseba.jp.fragment.tab;

/**
 * Created by danielnguyen on 8/22/17.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.eseba.jp.Config;
import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.adapter.ThreeNewsAdapter;
import com.eseba.jp.bus.data.RowNewsOnClick;
import com.eseba.jp.bus.listener.OnLoadingCompletedListener;
import com.eseba.jp.bus.listener.OnRowNewsClickListener;
import com.eseba.jp.business.BannerBusiness;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.business.NewsBusiness;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.News;
import com.eseba.jp.database.table.ThreeNews;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AllTabFragment extends BaseTabFragment
    implements OnRowNewsClickListener, OnLoadingCompletedListener {
    private static final String TAG = AllTabFragment.class.getSimpleName();

    @BindView(R.id.fragment_all_tab_news_list_recycler_view)
    protected RecyclerView newsListRecyclerView;
    @BindView(R.id.fragment_all_tab_news_content)
    protected WebView newsContentWebView;
    @BindView(R.id.fragment_all_tab_loading_bar)
    protected SpinKitView loadingProgressBar;

    private int pagePosition;
    private List<Banner> bannerList;
    private List<Genre> genreList;
    private List<News> newsList;
    private List<ThreeNews> threeNewsList;

    private BannerBusiness bannerBusiness;
    private GenreBusiness genreBusiness;
    private NewsBusiness newsBusiness;

    public static AllTabFragment newInstance(int page, String title) {
        AllTabFragment fragmentFirst = new AllTabFragment();
        Bundle args = new Bundle();
        args.putInt(Config.Extras.PAGE_POSITION, page);
        args.putString(Config.Extras.PAGE_TITLE, title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pagePosition = super.getArguments().getInt(Config.Extras.PAGE_POSITION, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tab, container, false);
        ButterKnife.bind(this, view);
        this.setupComponentViews(false);
        this.initData();
        this.setupRecyclerView();
        this.drawContentOfNewsList();
        return view;
    }

    @Override
    public void rowNewsOnClick(RowNewsOnClick data) {
        if (data.getPagePosition() == this.pagePosition) {
            this.setupComponentViews(true);
            super.loadNewsData(this.newsContentWebView, data.getNews());
        }
    }

    @Override
    protected void setupComponentViews(boolean isLoadingWebView) {
        this.isLoadingWebView = isLoadingWebView;
        super.drawComponentViews(
            this,
            this.newsListRecyclerView,
            this.newsContentWebView,
            this.loadingProgressBar
        );
    }

    @Override
    public void onLoadingCompletedListener() {
        this.newsContentWebView.setVisibility(View.VISIBLE);
        this.loadingProgressBar.setVisibility(View.GONE);
    }

    private void initData() {
        this.bannerBusiness = (BannerBusiness) ServiceRegistry.getService(BannerBusiness.TAG);
        this.genreBusiness = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
        this.newsBusiness = (NewsBusiness) ServiceRegistry.getService(NewsBusiness.TAG);

        this.bannerList = this.bannerBusiness.getBannerListFromDatabase();
        this.genreList = this.genreBusiness.getActiveGenres();
        this.newsList = this.newsBusiness.getNewsOfAllTab();
        this.threeNewsList = this.newsBusiness.splitNewsListByGenre(this.newsList, this.genreList);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.newsListRecyclerView.setLayoutManager(layoutManager);
        this.newsListRecyclerView.setHasFixedSize(false);
    }

    private void drawContentOfNewsList() {
        if (this.threeNewsList != null) {
            ThreeNewsAdapter adapter = new ThreeNewsAdapter(
                super.getActivity(),
                this.bannerList,
                this.threeNewsList,
                this.pagePosition,
                this
            );
            this.newsListRecyclerView.setAdapter(adapter);
        }
    }
}
