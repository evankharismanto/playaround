package com.backendDevelopment.withtest.dbrestservice.migrations;

import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.stereotype.Service;

@FlywayDataSource
@Log4j2
@Service
public class ExecuteSql {
    @Autowired
    private Flyway flyway;

    public void executeBaseline(String userId){
        log.warn("start");
        log.debug(String.format("start userid= %s",userId));
        flyway.baseline();
        log.warn("end");
    }

    public void executeFlyway(String userId){
        log.warn("start");
        log.debug(String.format("start userid= %s",userId));
        flyway.migrate();
        log.warn("end");
    }
}
