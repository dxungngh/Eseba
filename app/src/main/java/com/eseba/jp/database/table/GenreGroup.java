package com.eseba.jp.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by danielnguyen on 9/20/17.
 */

@DatabaseTable(tableName = "genre_group")
public class GenreGroup {
    private static final String TAG = GenreGroup.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String GENRE_GROUP = "GENRE_GROUP";
        public static final String GENRE_GROUP_NAME = "GENRE_GROUP_NAME";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.GENRE_GROUP)
    private String genreGroup;
    @DatabaseField(columnName = Fields.GENRE_GROUP_NAME)
    private String genreGroupName;
    private List<Genre> genreList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("genregroup")
    public String getGenreGroup() {
        return genreGroup;
    }

    @JsonProperty("genregroup")
    public void setGenreGroup(String genreGroup) {
        this.genreGroup = genreGroup;
    }

    @JsonProperty("genregroupname")
    public String getGenreGroupName() {
        return genreGroupName;
    }

    @JsonProperty("genregroupname")
    public void setGenreGroupName(String genreGroupName) {
        this.genreGroupName = genreGroupName;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @Override
    public String toString() {
        return "GenreGroup{" +
            "id=" + id +
            ", genreGroup='" + genreGroup + '\'' +
            ", genreGroupName='" + genreGroupName + '\'' +
            ", genreList=" + genreList +
            '}';
    }
}
