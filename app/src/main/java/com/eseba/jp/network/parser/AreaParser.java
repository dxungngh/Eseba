package com.eseba.jp.network.parser;

import android.text.TextUtils;
import android.util.Log;

import com.eseba.jp.database.table.Area;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class AreaParser extends BaseParser {
    public static final String TAG = AreaParser.class.getSimpleName();

    public List<Area> getAreaList(String response) throws Exception {
        List<Area> areas = new ArrayList<>();
        String message = super.getMessage(response);
        if (!TextUtils.isEmpty(message)) {
            throw new Exception(message);
        } else {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("areainfo");
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject areaObject = jsonArray.getJSONObject(i);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Area area = mapper.readValue(areaObject.toString(), Area.class);
                    areas.add(area);
                }
            } catch (Exception e) {
                Log.e(TAG, "getAreaList", e);
            }
        }
        return areas;
    }
}
