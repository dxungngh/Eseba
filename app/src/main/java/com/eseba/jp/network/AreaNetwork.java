package com.eseba.jp.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eseba.jp.Config;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.listener.OnGetAreaListListener;
import com.eseba.jp.network.parser.AreaParser;
import com.eseba.jp.network.volley.MyStringRequest;
import com.eseba.jp.network.volley.MyVolley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaNetwork extends BaseNetwork {
    public static final String TAG = AreaNetwork.class.getSimpleName();

    private MyVolley volley;
    private AreaParser parser;

    public AreaNetwork() {
        this.volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        this.parser = (AreaParser) ServiceRegistry.getService(AreaParser.TAG);
    }

    public void getAreaList(final OnGetAreaListListener listener) {
        String url = super.getUrl("/api/areainfo/");
        MyStringRequest request = new MyStringRequest(StringRequest.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Area> areaList = parser.getAreaList(response);
                        listener.onSuccess(areaList);
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