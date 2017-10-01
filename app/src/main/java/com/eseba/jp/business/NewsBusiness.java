package com.eseba.jp.business;

import android.text.TextUtils;
import android.util.Log;

import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.datasource.AreaDataSource;
import com.eseba.jp.database.datasource.GenreDataSource;
import com.eseba.jp.database.datasource.NewsDataSource;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.News;
import com.eseba.jp.database.table.ThreeNews;
import com.eseba.jp.listener.OnGetNewsListListener;
import com.eseba.jp.network.NewsNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class NewsBusiness {
    public static final String TAG = NewsBusiness.class.getSimpleName();

    private NewsNetwork network;
    private NewsDataSource newsDataSource;
    private AreaDataSource areaDataSource;
    private GenreDataSource genreDataSource;

    public NewsBusiness() {
        this.network = (NewsNetwork) ServiceRegistry.getService(NewsNetwork.TAG);
        this.newsDataSource = (NewsDataSource) ServiceRegistry.getService(NewsDataSource.TAG);
        this.areaDataSource = (AreaDataSource) ServiceRegistry.getService(AreaDataSource.TAG);
        this.genreDataSource = (GenreDataSource) ServiceRegistry.getService(GenreDataSource.TAG);
    }

    public void getNewsList(final OnGetNewsListListener listener) {
        List<Area> areaList = this.areaDataSource.getActiveAreas();
        List<Genre> genreList = this.genreDataSource.getActiveGenres();
        this.network.getNewsList(areaList, genreList, new OnGetNewsListListener() {
            @Override
            public void onSuccess(List<News> result) {
                if (result != null && result.size() > 0) {
                    newsDataSource.clearTable();
                    for (News news : result) {
                        newsDataSource.createNews(news);
                    }
                    listener.onSuccess(result);
                } else {
                    listener.onFailed(new Exception());
                }
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }

    public void getNewsListOfGenre(Genre genre, final OnGetNewsListListener listener) {
        this.network.getNewsListOfGenre(genre, new OnGetNewsListListener() {
            @Override
            public void onSuccess(List<News> result) {
                if (result != null && result.size() > 0) {
                    listener.onSuccess(result);
                } else {
                    Log.i(TAG, "onSuccess: " + result);
                    listener.onFailed(new Exception());
                }
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }

    public List<News> getNewsOfAllTab() {
        return this.newsDataSource.getNewsOfAllTab();
    }

    public List<ThreeNews> splitNewsListByGenre(List<News> newsList, List<Genre> genreList) {
        if (newsList == null || newsList.size() <= 0 ||
            genreList == null || genreList.size() <= 0) {
            return null;
        }
        List<ThreeNews> threeNewsList = new ArrayList<>();
        for (Genre genre : genreList) {
            ThreeNews threeNews = new ThreeNews();
            String genreCode = genre.getGenreCode();
            if (!TextUtils.isEmpty(genreCode)) {
                threeNews.setNewsTypeName(genre.getGenreName());
                for (News news : newsList) {
                    String newsGenreCode = news.getGenreCodeString();
                    if (newsGenreCode != null && newsGenreCode.contains(genreCode)) {
                        threeNews.addNews(news);
                    }
                }
            }
            if (!threeNews.isEmpty()) {
                threeNewsList.add(threeNews);
            }
        }
        return threeNewsList;
    }
}
