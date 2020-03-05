package com.backendDevelopment.withtest.dbrestservice.customactuator;

import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import com.backendDevelopment.withtest.dbrestservice.migrations.ExecuteSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.boot.actuate.flyway.FlywayEndpoint;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@EndpointWebExtension(endpoint = FlywayEndpoint.class)
public class flywayActuator {
    @Autowired
    private ExecuteSql executeMigrate;

    @ReadOperation
    public void flywayOperation(@Selector String operation) {
        switch(operation) {
            case "baselinedb":
                log.warn("start");
                executeMigrate.executeBaseline("actuator");
                log.warn("end");
                break;
            case "migratedb":
                log.warn("start");
                executeMigrate.executeFlyway("actuator");
                log.warn("end");
                break;
        }
    }
}