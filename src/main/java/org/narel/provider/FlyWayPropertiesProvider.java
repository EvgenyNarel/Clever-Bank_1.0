package org.narel.provider;

import org.narel.properties.FlyWayProperties;

public class FlyWayPropertiesProvider extends PropertiesObjectProvider<FlyWayProperties>{

    @Override
    protected String prefix() {
        return "flyway";
    }

    @Override
    protected Class<FlyWayProperties> clazz() {
        return FlyWayProperties.class;
    }
}
