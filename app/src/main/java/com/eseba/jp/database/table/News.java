package com.eseba.jp.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by danielnguyen on 8/23/17.
 */

@DatabaseTable(tableName = "news")
public class News {
    private static final String TAG = News.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String AREA_CODE = "AREA_CODE";
        public static final String GENRE_CODE_STRING = "GENRE_CODE_STRING";
        public static final String TITLE = "TITLE";
        public static final String DATE = "DATE";
        public static final String IMAGE_URL = "IMAGE_URL";
        public static final String URL = "URL";
        public static final String POSTING_DATE = "POSTING_DATE";
        public static final String IS_BARGAIN = "IS_BARGAIN";
        public static final String IS_NEW_OPEN = "IS_NEW_OPEN";
        public static final String IS_NEW_RELEASE = "IS_NEW_RELEASE";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.AREA_CODE)
    private String areaCode;
    private List<String> genreCode;
    @DatabaseField(columnName = Fields.GENRE_CODE_STRING)
    private String genreCodeString;
    @DatabaseField(columnName = Fields.TITLE)
    private String title;
    @DatabaseField(columnName = Fields.DATE)
    private String date;
    @DatabaseField(columnName = Fields.IMAGE_URL)
    private String imageUrl;
    @DatabaseField(columnName = Fields.URL)
    private String url;
    @DatabaseField(columnName = Fields.POSTING_DATE)
    private String postingDate;
    @DatabaseField(columnName = Fields.IS_BARGAIN)
    private int isBargain;
    @DatabaseField(columnName = Fields.IS_NEW_OPEN)
    private int isNewOpen;
    @DatabaseField(columnName = Fields.IS_NEW_RELEASE)
    private int isNewRelease;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("areacode")
    public String getAreaCode() {
        return areaCode;
    }

    @JsonProperty("areacode")
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @JsonProperty("genrecode")
    public List<String> getGenreCode() {
        return genreCode;
    }

    @JsonProperty("genrecode")
    public void setGenreCode(List<String> genreCode) {
        this.genreCode = genreCode;
    }

    public String getGenreCodeString() {
        return genreCodeString;
    }

    public void setGenreCodeString(String genreCodeString) {
        this.genreCodeString = genreCodeString;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
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

    @JsonProperty("posting_date")
    public String getPostingDate() {
        return postingDate;
    }

    @JsonProperty("posting_date")
    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    @JsonProperty("is_bargain")
    public int getIsBargain() {
        return isBargain;
    }

    @JsonProperty("is_bargain")
    public void setIsBargain(int isBargain) {
        this.isBargain = isBargain;
    }

    @JsonProperty("is_newopen")
    public int getIsNewOpen() {
        return isNewOpen;
    }

    @JsonProperty("is_newopen")
    public void setIsNewOpen(int isNewOpen) {
        this.isNewOpen = isNewOpen;
    }

    @JsonProperty("is_newrelease")
    public int getIsNewRelease() {
        return isNewRelease;
    }

    @JsonProperty("is_newrelease")
    public void setIsNewRelease(int isNewRelease) {
        this.isNewRelease = isNewRelease;
    }

    public void convertGenreCodeToString() {
        this.genreCodeString = "";
        if (this.genreCode != null && this.genreCode.size() > 0) {
            for (String code : this.genreCode) {
                this.genreCodeString += code + ",";
            }
        }
    }

    @Override
    public String toString() {
        return "News{" +
            "id=" + id +
            ", areaCode='" + areaCode + '\'' +
            ", genreCode=" + genreCode +
            ", genreCodeString='" + genreCodeString + '\'' +
            ", title='" + title + '\'' +
            ", date='" + date + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", url='" + url + '\'' +
            ", postingDate='" + postingDate + '\'' +
            ", isBargain=" + isBargain +
            ", isNewOpen=" + isNewOpen +
            ", isNewRelease=" + isNewRelease +
            '}';
    }
}
