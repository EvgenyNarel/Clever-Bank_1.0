package org.narel.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

abstract class PropertiesObjectProvider<T> {

    private static final Map<?, ?> properties;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public T propertiesObject() {
        return propertiesObject;
    }

    private final T propertiesObject;

    static {
        properties = Optional.ofNullable(PropertiesObjectProvider.class.getClassLoader().getResourceAsStream("application.yml"))
                .<Map<String, Object>>map(stream -> new Yaml().load(stream))
                .orElseGet(Collections::emptyMap);
    }

    public PropertiesObjectProvider() {
        this.propertiesObject = getPropertiesObject();
    }

    protected abstract String prefix();
    protected abstract Class<T> clazz();

    private T getPropertiesObject() {
        Object mapObject = properties;
        Object rawObject = mapObject;
        for (String key : prefix().split("\\.")) {
            if (mapObject instanceof Map<?, ?>) {
                rawObject = ((Map<?, ?>) mapObject).get(key);
                mapObject = rawObject;
            } else {
                throw new IllegalArgumentException("Properties prefix '" + prefix() + "' isn't correct");
            }
        }

        return objectMapper.convertValue(rawObject, clazz());
    }
}