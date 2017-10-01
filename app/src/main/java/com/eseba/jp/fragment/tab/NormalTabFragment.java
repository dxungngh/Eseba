package com.eseba.jp.fragment.tab;

/**
 * Created by danielnguyen on 8/22/17.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.eseba.jp.Config;
import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.adapter.NewsAdapter;
import com.eseba.jp.bus.data.RowNewsOnClick;
import com.eseba.jp.bus.listener.OnLoadingCompletedListener;
import com.eseba.jp.bus.listener.OnRowNewsClickListener;
import com.eseba.jp.business.NewsBusiness;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.News;
import com.eseba.jp.listener.OnGetNewsListListener;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class NormalTabFragment extends BaseTabFragment
    implements OnRowNewsClickListener, OnLoadingCompletedListener {
    private static final String TAG = NormalTabFragment.class.getSimpleName();

    @BindView(R.id.fragment_normal_tab_news_list_recycler_view)
    protected RecyclerView newsListRecyclerView;
    @BindView(R.id.fragment_normal_tab_news_content)
    protected WebView newsContentWebView;
    @BindView(R.id.fragment_normal_tab_loading_bar)
    protected SpinKitView loadingProgressBar;

    private NewsBusiness newsBusiness;

    private Genre genre;
    private int pagePosition;
    private List<News> newsList;

    public static NormalTabFragment newInstance(int page, Genre genre) {
        NormalTabFragment fragmentFirst = new NormalTabFragment();
        Bundle args = new Bundle();
        args.putInt(Config.Extras.PAGE_POSITION, page);
        args.putSerializable(Config.Extras.PAGE_GENRE, genre);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pagePosition = super.getArguments().getInt(Config.Extras.PAGE_POSITION, 0);
        this.genre = (Genre) super.getArguments().getSerializable(Config.Extras.PAGE_GENRE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal_tab, container, false);
        ButterKnife.bind(this, view);
        this.setupComponentViews(false);
        this.setupRecyclerView();
        this.initData();
        this.getNewsList();
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
        this.newsBusiness = (NewsBusiness) ServiceRegistry.getService(NewsBusiness.TAG);
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

    private void getNewsList() {
        this.loadingProgressBar.setVisibility(View.VISIBLE);
        this.newsBusiness.getNewsListOfGenre(
            this.genre,
            new OnGetNewsListListener() {
                @Override
                public void onSuccess(List<News> result) {
                    newsList = result;
                    drawNewsList();
                }

                @Override
                public void onFailed(Throwable error) {
                    Log.e(TAG, "getNewsList", error);
                }
            }
        );
    }

    private void drawNewsList() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                loadingProgressBar.setVisibility(View.GONE);
                NewsAdapter newsAdapter = new NewsAdapter(
                    getActivity(),
                    newsList,
                    genre.getGenreName(),
                    pagePosition,
                    NormalTabFragment.this
                );
                newsListRecyclerView.setAdapter(newsAdapter);
            }
        });
    }
}
