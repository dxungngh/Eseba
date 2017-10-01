package com.eseba.jp.bus.data;

import com.eseba.jp.database.table.News;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class RowNewsOnClick {
    private static final String TAG = RowNewsOnClick.class.getSimpleName();

    private News news;
    private int pagePosition;

    public RowNewsOnClick(News news, int pagePosition) {
        this.news = news;
        this.pagePosition = pagePosition;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(int pagePosition) {
        this.pagePosition = pagePosition;
    }

    @Override
    public String toString() {
        return "RowNewsOnClick{" +
            "news=" + news +
            ", pagePosition=" + pagePosition +
            '}';
    }
}
