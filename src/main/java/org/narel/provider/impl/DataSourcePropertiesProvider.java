package org.narel.provider.impl;

import org.narel.properties.DatasourceProperties;

public class DataSourcePropertiesProvider extends AbstractPropertiesObjectProvider<DatasourceProperties> {

    @Override
    protected String prefix() {
        return "datasource";
    }

    @Override
    protected Class<DatasourceProperties> clazz() {
        return DatasourceProperties.class;
    }
}
