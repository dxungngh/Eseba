package com.eseba.jp.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by danielnguyen on 9/20/17.
 */

@DatabaseTable(tableName = "genre")
public class Genre implements Serializable {
    private static final String TAG = Genre.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String GENRE_CODE = "GENRE_CODE";
        public static final String GENRE_NAME = "GENRE_NAME";
        public static final String GENRE_GROUP_NAME = "GENRE_GROUP_NAME";
        public static final String IS_ACTIVE = "IS_ACTIVE";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.GENRE_CODE)
    private String genreCode;
    @DatabaseField(columnName = Fields.GENRE_NAME)
    private String genreName;
    @DatabaseField(columnName = Fields.GENRE_GROUP_NAME)
    private String genreGroupName;
    @DatabaseField(columnName = Fields.IS_ACTIVE)
    private boolean isActive = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("genrecode")
    public String getGenreCode() {
        return genreCode;
    }

    @JsonProperty("genrecode")
    public void setGenreCode(String genreCode) {
        this.genreCode = genreCode;
    }

    @JsonProperty("genrename")
    public String getGenreName() {
        return genreName;
    }

    @JsonProperty("genrename")
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreGroupName() {
        return genreGroupName;
    }

    public void setGenreGroupName(String genreGroupName) {
        this.genreGroupName = genreGroupName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "id=" + id +
            ", genreCode='" + genreCode + '\'' +
            ", genreName='" + genreName + '\'' +
            ", genreGroupName='" + genreGroupName + '\'' +
            ", isActive=" + isActive +
            '}';
    }
}
