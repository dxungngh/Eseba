package com.eseba.jp.business;

import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.datasource.ConfigurationDataSource;
import com.eseba.jp.database.table.Configuration;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class ConfigurationBusiness {
    public static final String TAG = ConfigurationBusiness.class.getSimpleName();

    private ConfigurationDataSource dataSource;

    public ConfigurationBusiness() {
        this.dataSource = (ConfigurationDataSource) ServiceRegistry.getService(ConfigurationDataSource.TAG);
    }

    public Configuration getConfiguration() {
        return this.dataSource.getConfiguration();
    }

    public void createConfiguration(Configuration configuration) {
        this.dataSource.createConfiguration(configuration);
    }

    public void updateConfiguration(Configuration configuration) {
        configuration.setId(1);
        this.dataSource.updateConfiguration(configuration);
    }
}
