package com.eseba.jp.network.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by danielnguyen on 8/21/17.
 */

public class MyVolley {
    public static final String TAG = MyVolley.class.getSimpleName();

    private RequestQueue requestQueue;

    public MyVolley(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        this.requestQueue.add(request);
    }
}
