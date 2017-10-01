package com.eseba.jp.network.parser;

import android.text.TextUtils;
import android.util.Log;

import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.GenreGroup;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class GenreParser extends BaseParser {
    public static final String TAG = GenreParser.class.getSimpleName();

    public List<GenreGroup> getGenreGroupList(String response) throws Exception {
        List<GenreGroup> genreGroupList = new ArrayList<>();
        String message = super.getMessage(response);
        if (!TextUtils.isEmpty(message)) {
            throw new Exception(message);
        } else {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("genreinfo");
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    GenreGroup genreGroup = mapper.readValue(object.toString(), GenreGroup.class);
                    genreGroup.setGenreList(this.getGenreList(object.getJSONArray("genrelist")));
                    genreGroupList.add(genreGroup);
                }
            } catch (Exception e) {
                Log.e(TAG, "getGenreGroupList", e);
            }
        }
        return genreGroupList;
    }

    private List<Genre> getGenreList(JSONArray genreListJsonArray) throws Exception {
        List<Genre> genreList = new ArrayList<>();
        for (int i = 0; i < genreListJsonArray.length(); i++) {
            try {
                JSONObject genreObject = genreListJsonArray.getJSONObject(i);
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Genre genre = mapper.readValue(genreObject.toString(), Genre.class);
                genreList.add(genre);
            } catch (Exception e) {
                Log.e(TAG, "getGenreList", e);
            }
        }
        return genreList;
    }
}
