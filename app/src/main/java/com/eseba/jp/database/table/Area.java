package com.eseba.jp.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by danielnguyen on 9/19/17.
 */

@DatabaseTable(tableName = "area")
public class Area {
    private static final String TAG = Area.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String STATE_CODE = "STATE_CODE";
        public static final String STATE_NAME = "STATE_NAME";
        public static final String AREA_GROUP = "AREA_GROUP";
        public static final String AREA_GROUP_NAME = "AREA_GROUP_NAME";
        public static final String AREA_CODE = "AREA_CODE";
        public static final String AREA_NAME = "AREA_NAME";
        public static final String IS_ACTIVE = "IS_ACTIVE";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.STATE_CODE)
    private String stateCode;
    @DatabaseField(columnName = Fields.STATE_NAME)
    private String sateName;
    @DatabaseField(columnName = Fields.AREA_GROUP)
    private String areaGroup;
    @DatabaseField(columnName = Fields.AREA_GROUP_NAME)
    private String areaGroupName;
    @DatabaseField(columnName = Fields.AREA_CODE)
    private String areaCode;
    @DatabaseField(columnName = Fields.AREA_NAME)
    private String areaName;
    @DatabaseField(columnName = Fields.IS_ACTIVE)
    private boolean isActive = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("statecode")
    public String getStateCode() {
        return stateCode;
    }

    @JsonProperty("statecode")
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @JsonProperty("statename")
    public String getSateName() {
        return sateName;
    }

    @JsonProperty("statename")
    public void setSateName(String sateName) {
        this.sateName = sateName;
    }

    @JsonProperty("areagroup")
    public String getAreaGroup() {
        return areaGroup;
    }

    @JsonProperty("areagroup")
    public void setAreaGroup(String areaGroup) {
        this.areaGroup = areaGroup;
    }

    @JsonProperty("areagroupname")
    public String getAreaGroupName() {
        return areaGroupName;
    }

    @JsonProperty("areagroupname")
    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
    }

    @JsonProperty("areacode")
    public String getAreaCode() {
        return areaCode;
    }

    @JsonProperty("areacode")
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @JsonProperty("areaname")
    public String getAreaName() {
        return areaName;
    }

    @JsonProperty("areaname")
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + id +
            ", stateCode='" + stateCode + '\'' +
            ", sateName='" + sateName + '\'' +
            ", areaGroup='" + areaGroup + '\'' +
            ", areaGroupName='" + areaGroupName + '\'' +
            ", areaCode='" + areaCode + '\'' +
            ", areaName='" + areaName + '\'' +
            ", isActive=" + isActive +
            '}';
    }
}
