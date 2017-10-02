package com.eseba.jp.database.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by danielnguyen on 10/2/17.
 */

@DatabaseTable(tableName = "configuration")
public class Configuration {
    private static final String TAG = Configuration.class.getSimpleName();

    public static class Fields {
        public static final String ID = "ID";
        public static final String PUSH_NOTIFICATION = "PUSH_NOTIFICATION";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.PUSH_NOTIFICATION)
    private boolean isPushNotification;

    public Configuration() {
        this.id = 1;
        this.isPushNotification = true;
    }

    public Configuration(long id, boolean isPushNotification) {
        this.id = id;
        this.isPushNotification = isPushNotification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPushNotification() {
        return isPushNotification;
    }

    public void setPushNotification(boolean pushNotification) {
        isPushNotification = pushNotification;
    }

    @Override
    public String toString() {
        return "Configuration{" +
            "id=" + id +
            ", isPushNotification=" + isPushNotification +
            '}';
    }
}
