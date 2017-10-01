package com.eseba.jp.business;

import android.text.TextUtils;

import com.eseba.jp.Config;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.datasource.GenreDataSource;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.database.table.GenreGroup;
import com.eseba.jp.listener.OnGetGenreGroupListListener;
import com.eseba.jp.network.GenreNetwork;

import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class GenreBusiness {
    public static final String TAG = GenreBusiness.class.getSimpleName();

    private GenreNetwork network;
    private GenreDataSource genreDataSource;

    public GenreBusiness() {
        this.network = (GenreNetwork) ServiceRegistry.getService(GenreNetwork.TAG);
        this.genreDataSource = (GenreDataSource) ServiceRegistry.getService(GenreDataSource.TAG);
    }

    public void getGenreGroupList(final OnGetGenreGroupListListener listener) {
        this.network.getGenreGroupList(new OnGetGenreGroupListListener() {
            @Override
            public void onSuccess(List<GenreGroup> result) {
                saveGenreGroupList(result);
                listener.onSuccess(result);
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }

    public List<Genre> getActiveGenres() {
        return this.genreDataSource.getActiveGenres();
    }

    private void saveGenreGroupList(List<GenreGroup> genreGroupList) {
        this.removeUnusedGenreInDatabase(genreGroupList);
        List<Genre> genreListDatabase = this.genreDataSource.getActiveGenres();
        for (GenreGroup genreGroup : genreGroupList) {
            if (this.isAvailableGenreGroup(genreGroup)) {
                for (Genre genre : genreGroup.getGenreList()) {
                    if (!this.isInList(genre, genreListDatabase)) {
                        genre.setGenreGroupName(genreGroup.getGenreGroup());
                        this.genreDataSource.createGenre(genre);
                    }
                }
            }
        }
    }

    private void removeUnusedGenreInDatabase(List<GenreGroup> genreGroupList) {
        List<Genre> genreListDatabase = this.genreDataSource.getActiveGenres();
        for (Genre genre : genreListDatabase) {
            for (GenreGroup genreGroup : genreGroupList) {
                if (this.isAvailableGenreGroup(genreGroup)) {
                    if (!this.isInList(genre, genreGroup.getGenreList())) {
                        this.genreDataSource.deleteGenre(genre);
                    }
                }
            }
        }
    }

    private boolean isAvailableGenreGroup(GenreGroup genreGroup) {
        if (genreGroup != null) {
            String type = genreGroup.getGenreGroup();
            if (!TextUtils.isEmpty(type)) {
                if (type.toLowerCase().equals(Config.Network.EVENT) ||
                    type.toLowerCase().equals(Config.Network.SEMINAR)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInList(Genre genre, List<Genre> genreList) {
        if (genre == null || TextUtils.isEmpty(genre.getGenreCode())) {
            return true;
        }
        if (genreList != null && genreList.size() > 0) {
            for (Genre genreDatabase : genreList) {
                String code = genreDatabase.getGenreCode();
                if (!TextUtils.isEmpty(code) && genre.getGenreCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }
}
