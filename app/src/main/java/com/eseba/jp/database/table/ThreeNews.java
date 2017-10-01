package com.eseba.jp.database.table;

import java.util.Arrays;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class ThreeNews {
    private static final String TAG = ThreeNews.class.getSimpleName();
    private static final int MAX_NEWS = 3;

    private News[] newsList;
    private String newsTypeName;

    public ThreeNews() {
        newsList = new News[MAX_NEWS];
    }

    public ThreeNews(News[] newsList, String newsTypeName) {
        this.newsList = newsList;
        this.newsTypeName = newsTypeName;
    }

    public News[] getNewsList() {
        return newsList;
    }

    public void setNewsList(News[] newsList) {
        this.newsList = newsList;
    }

    public String getNewsTypeName() {
        return newsTypeName;
    }

    public void setNewsTypeName(String newsTypeName) {
        this.newsTypeName = newsTypeName;
    }

    public void addNews(News news) {
        for (int i = 0; i < MAX_NEWS; i++) {
            if (this.newsList[i] == null) {
                this.newsList[i] = news;
                break;
            }
        }
    }

    public boolean isEmpty() {
        for (News news : this.newsList) {
            if (news != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ThreeNews{" +
            "newsList=" + Arrays.toString(newsList) +
            ", newsTypeName='" + newsTypeName + '\'' +
            '}';
    }
}
