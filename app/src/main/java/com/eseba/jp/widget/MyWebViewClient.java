package com.eseba.jp.widget;

import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.eseba.jp.Config;
import com.eseba.jp.bus.listener.OnLoadingCompletedListener;

/**
 * Created by danielnguyen on 9/29/17.
 */

public class MyWebViewClient extends WebViewClient {
    private OnLoadingCompletedListener listener;

    public MyWebViewClient(OnLoadingCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view,
                                          HttpAuthHandler handler,
                                          String host,
                                          String realm) {
        handler.proceed(Config.Network.USERNAME, Config.Network.PASSWORD);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.listener.onLoadingCompletedListener();
    }
}
