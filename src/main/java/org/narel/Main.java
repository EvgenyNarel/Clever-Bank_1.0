package org.narel;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.narel.properties.FlyWayProperties;
import org.narel.provider.FlyWayPropertiesProvider;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        flyWayMigration();
    }

    private static void flyWayMigration() {
        FlyWayPropertiesProvider flyWayPropertiesProvider = new FlyWayPropertiesProvider();
        FlyWayProperties flyWayProperties = flyWayPropertiesProvider.propertiesObject();

        FluentConfiguration configure = Flyway.configure();
        configure.configuration(
                Map.of(
                        "flyway.url", flyWayProperties.getUrl(),
                        "flyway.user", flyWayProperties.getUser(),
                        "flyway.password", flyWayProperties.getPassword()
                )
        );
        configure.load().migrate();
    }
}