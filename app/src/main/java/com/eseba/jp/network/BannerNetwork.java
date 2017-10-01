package com.eseba.jp.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eseba.jp.Config;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.listener.OnGetBannerListListener;
import com.eseba.jp.network.parser.BannerParser;
import com.eseba.jp.network.volley.MyStringRequest;
import com.eseba.jp.network.volley.MyVolley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerNetwork extends BaseNetwork {
    public static final String TAG = BannerNetwork.class.getSimpleName();

    private MyVolley volley;
    private BannerParser parser;

    public BannerNetwork() {
        this.volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        this.parser = (BannerParser) ServiceRegistry.getService(BannerParser.TAG);
    }

    public void getBannerList(final OnGetBannerListListener listener) {
        String url = super.getUrl("/api/bannerinfo/");
        MyStringRequest request = new MyStringRequest(StringRequest.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Banner> bannerList = parser.getBannerList(response);
                        listener.onSuccess(bannerList);
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