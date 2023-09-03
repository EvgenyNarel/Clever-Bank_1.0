package org.narel.properties;

import lombok.Data;

@Data
public class DatasourceProperties {

    private String driverClassName;
    private String url;
    private String user;
    private String password;
}
