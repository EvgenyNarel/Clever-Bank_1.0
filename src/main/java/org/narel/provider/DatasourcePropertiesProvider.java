package org.narel.provider;

import org.narel.properties.DatasourceProperties;

public class DatasourcePropertiesProvider extends PropertiesObjectProvider<DatasourceProperties>{
    @Override
    protected String prefix() {
        return "datasource";
    }

    @Override
    protected Class<DatasourceProperties> clazz() {
        return DatasourceProperties.class;
    }
}
