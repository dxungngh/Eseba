package com.eseba.jp.fragment.tab;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import com.eseba.jp.R;
import com.eseba.jp.bus.listener.OnLoadingCompletedListener;
import com.eseba.jp.database.table.News;
import com.eseba.jp.widget.MyWebViewClient;
import com.github.ybq.android.spinkit.SpinKitView;

/**
 * Created by danielnguyen on 8/28/17.
 */

public abstract class BaseTabFragment extends Fragment {
    protected boolean isLoadingWebView = false;

    protected abstract void setupComponentViews(boolean isLoadingWebView);

    public boolean onBackPressed() {
        if (this.isLoadingWebView) {
            this.setupComponentViews(false);
            return false;
        } else {
            return true;
        }
    }

    protected void loadNewsData(WebView webView, News news) {
        String url = super.getString(R.string.website_link);
        if (news != null && !TextUtils.isEmpty(news.getUrl())) {
            url = news.getUrl();
        }
        webView.loadUrl(url);
    }

    protected void drawComponentViews(OnLoadingCompletedListener listener,
                                      RecyclerView newsListRecyclerView,
                                      WebView newsContentWebView,
                                      SpinKitView loadingProgressBar) {
        if (this.isLoadingWebView) {
            newsListRecyclerView.setVisibility(View.GONE);
            newsContentWebView.setVisibility(View.INVISIBLE);
            newsContentWebView.setWebViewClient(new MyWebViewClient(listener));
            newsContentWebView.getSettings().setJavaScriptEnabled(true);
            loadingProgressBar.setVisibility(View.VISIBLE);
        } else {
            newsListRecyclerView.setVisibility(View.VISIBLE);
            newsContentWebView.setVisibility(View.GONE);
            loadingProgressBar.setVisibility(View.GONE);
        }
    }
}
