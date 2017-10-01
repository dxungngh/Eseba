package com.eseba.jp.network.parser;

import android.text.TextUtils;
import android.util.Log;

import com.eseba.jp.database.table.Banner;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class BannerParser extends BaseParser {
    public static final String TAG = BannerParser.class.getSimpleName();

    public List<Banner> getBannerList(String response) throws Exception {
        List<Banner> banners = new ArrayList<>();
        String message = super.getMessage(response);
        if (!TextUtils.isEmpty(message)) {
            throw new Exception(message);
        } else {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("bannerinfo");
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject bannerObject = jsonArray.getJSONObject(i);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Banner banner = mapper.readValue(bannerObject.toString(), Banner.class);
                    banners.add(banner);
                }
            } catch (Exception e) {
                Log.e(TAG, "parse", e);
            }
        }
        return banners;
    }
}
