package org.narel.provider.impl;

import org.narel.properties.FlyWayProperties;
import org.narel.provider.PropertiesObjectProvider;

public class FlyWayPropertiesProvider extends AbstractPropertiesObjectProvider<FlyWayProperties> {

    @Override
    protected String prefix() {
        return "flyway";
    }

    @Override
    protected Class<FlyWayProperties> clazz() {
        return FlyWayProperties.class;
    }
}
