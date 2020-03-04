package com.backendDevelopment.withtest.dbrestservice;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfiguration {
    /*@Bean
    public Flyway flyway(){
        Flyway flyway = new Flyway(new FluentConfiguration());
        return flyway;
    }*/

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            // do nothing
        };
    }
}
