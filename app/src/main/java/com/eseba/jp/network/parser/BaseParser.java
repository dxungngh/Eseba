package com.eseba.jp.network.parser;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class BaseParser {
    private static final String TAG = BaseParser.class.getSimpleName();

    protected String getMessage(String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONObject resultObject = object.getJSONObject("result");
            String message = resultObject.getString("message");
            return message;
        } catch (Exception e) {
            Log.e(TAG, "getMessage", e);
            return null;
        }
    }
}
