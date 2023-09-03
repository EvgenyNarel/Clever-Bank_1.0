package org.narel.provider.impl;


import org.narel.properties.PercentsProperties;

public class PercentsPropertiesProvider extends AbstractPropertiesObjectProvider<PercentsProperties> {

    @Override
    protected String prefix() {
        return "percents";
    }

    @Override
    protected Class<PercentsProperties> clazz() {
        return PercentsProperties.class;
    }
}