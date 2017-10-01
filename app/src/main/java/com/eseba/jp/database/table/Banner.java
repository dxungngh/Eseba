package com.eseba.jp.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by danielnguyen on 9/17/17.
 */

@DatabaseTable(tableName = "banner")
public class Banner {
    private static final String TAG = Banner.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String TITLE = "TITLE";
        public static final String COMMENT = "COMMENT";
        public static final String IMAGE_URL = "IMAGE_URL";
        public static final String URL = "URL";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.TITLE)
    private String title;
    @DatabaseField(columnName = Fields.COMMENT)
    private String comment;
    @DatabaseField(columnName = Fields.IMAGE_URL)
    private String imageUrl;
    @DatabaseField(columnName = Fields.URL)
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("imageurl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageurl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Banner{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", comment='" + comment + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", url='" + url + '\'' +
            '}';
    }
}
