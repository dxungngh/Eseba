package com.eseba.jp.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eseba.jp.Config;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.table.GenreGroup;
import com.eseba.jp.listener.OnGetGenreGroupListListener;
import com.eseba.jp.network.parser.GenreParser;
import com.eseba.jp.network.volley.MyStringRequest;
import com.eseba.jp.network.volley.MyVolley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreNetwork extends BaseNetwork {
    public static final String TAG = GenreNetwork.class.getSimpleName();

    private MyVolley volley;
    private GenreParser parser;

    public GenreNetwork() {
        this.volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        this.parser = (GenreParser) ServiceRegistry.getService(GenreParser.TAG);
    }

    public void getGenreGroupList(final OnGetGenreGroupListListener listener) {
        String url = super.getUrl("/api/genreinfo/");
        MyStringRequest request = new MyStringRequest(StringRequest.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<GenreGroup> genreGroupList = parser.getGenreGroupList(response);
                        listener.onSuccess(genreGroupList);
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
                return params;
            }
        };
        this.volley.addToRequestQueue(request);
    }
}