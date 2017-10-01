package com.eseba.jp.network.volley;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.eseba.jp.Config;

import java.util.HashMap;
import java.util.Map;

public class MyStringRequest extends StringRequest {
    public MyStringRequest(int method, String url,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        retryPolicy = new DefaultRetryPolicy(
            Config.Network.HTTP_REQUEST_TIMEOUT_MS,
            Config.Network.MAX_RETRY_NUMBER,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return super.setRetryPolicy(retryPolicy);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> params = new HashMap<>();
        String creds = String.format("%s:%s", Config.Network.USERNAME, Config.Network.PASSWORD);
        String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
        params.put("Authorization", auth);
        return params;
    }
}