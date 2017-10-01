package com.eseba.jp.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eseba.jp.Config;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.News;
import com.eseba.jp.listener.OnGetNewsListListener;
import com.eseba.jp.network.parser.NewsParser;
import com.eseba.jp.network.volley.MyStringRequest;
import com.eseba.jp.network.volley.MyVolley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsNetwork extends BaseNetwork {
    public static final String TAG = NewsNetwork.class.getSimpleName();

    private MyVolley volley;
    private NewsParser parser;

    public NewsNetwork() {
        this.volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        this.parser = (NewsParser) ServiceRegistry.getService(NewsParser.TAG);
    }

    public void getNewsList(final List<Area> activeAreas, final List<Genre> activeGenres, final OnGetNewsListListener listener) {
        String url = super.getUrl("/api/newinfo/");
        MyStringRequest request = new MyStringRequest(StringRequest.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<News> newsList = parser.getNewsList(response);
                        listener.onSuccess(newsList);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.Network.SECURE_KEY, Config.Network.API_KEY);
//                if (activeAreas != null && activeAreas.size() > 0) {
//                    JSONArray areaCodes = new JSONArray();
//                    for (Area area : activeAreas) {
//                        areaCodes.put(area.getAreaCode());
//                    }
//                    params.put(Config.Network.AREA, areaCodes.toString());
//                }
//                if (activeGenres != null && activeGenres.size() > 0) {
//                    JSONArray genreCodes = new JSONArray();
//                    for (Genre genre : activeGenres) {
//                        genreCodes.put(genre.getGenreCode());
//                    }
//                    params.put(Config.Network.GENRE, genreCodes.toString());
//                }
                return params;
            }
        };
        this.volley.addToRequestQueue(request);
    }

    public void getNewsListOfGenre(final Genre genre, final OnGetNewsListListener listener) {
        String url = super.getUrl("/api/newinfo/");
        MyStringRequest request = new MyStringRequest(StringRequest.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<News> newsList = parser.getNewsList(response);
                        listener.onSuccess(newsList);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.Network.SECURE_KEY, Config.Network.API_KEY);
                params.put(Config.Network.GENRE, genre.getGenreCode());
                return params;
            }
        };
        this.volley.addToRequestQueue(request);
    }
}