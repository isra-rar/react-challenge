package com.livraria.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:db.properties")
@Profile("dev")
public class DataBaseProperties {

    private String JDBC_DATABASE_URL;
    private String JDBC_DATABASE_USERNAME;
    private String JDBC_DATABASE_PASSWORD;

    public String getJDBC_DATABASE_URL() {
        return JDBC_DATABASE_URL;
    }

    public void setJDBC_DATABASE_URL(String JDBC_DATABASE_URL) {
        this.JDBC_DATABASE_URL = JDBC_DATABASE_URL;
    }

    public String getJDBC_DATABASE_USERNAME() {
        return JDBC_DATABASE_USERNAME;
    }

    public void setJDBC_DATABASE_USERNAME(String JDBC_DATABASE_USERNAME) {
        this.JDBC_DATABASE_USERNAME = JDBC_DATABASE_USERNAME;
    }

    public String getJDBC_DATABASE_PASSWORD() {
        return JDBC_DATABASE_PASSWORD;
    }

    public void setJDBC_DATABASE_PASSWORD(String JDBC_DATABASE_PASSWORD) {
        this.JDBC_DATABASE_PASSWORD = JDBC_DATABASE_PASSWORD;
    }
}
