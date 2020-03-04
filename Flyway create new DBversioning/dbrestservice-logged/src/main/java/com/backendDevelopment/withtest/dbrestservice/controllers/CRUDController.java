package com.backendDevelopment.withtest.dbrestservice.controllers;

import com.backendDevelopment.withtest.dbrestservice.DbrestserviceApplication;
import com.backendDevelopment.withtest.dbrestservice.helper.JsonHelper;
import com.backendDevelopment.withtest.dbrestservice.migrations.ExecuteSql;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Log4j2
public class CRUDController {
    @Autowired
    private OrderService service;

    @Autowired
    private ExecuteSql executeMigrate;

    //private static final Logger log = LogManager.getLogger(DbrestserviceApplication.class);

    @GetMapping("/baselinedb")
    void baselineFlyway(@RequestHeader(name = "userId", required = true) String userId) {
        log.warn("start");
        log.debug(String.format("start userid= %s",userId));
        executeMigrate.executeBaseline(userId);
        log.warn("end");
    }

    @GetMapping("/migratedb")
    void executeFlyway(@RequestHeader(name = "userId", required = true) String userId) {
        log.warn("start");
        log.debug(String.format("start userid= %s",userId));
        executeMigrate.executeFlyway(userId);
        log.warn("end");
    }

    @PostMapping("/order")
    public @ResponseBody
    ResponseEntity<Order> create(@RequestHeader(name = "userId", required = true) String userId, @RequestBody @Valid Order order) throws JsonProcessingException {
        log.warn(String.format("start date= %s customerName= %s",order.getDate().toString(),order.getCustomer().getName()));
        log.debug(String.format("start userid= %s, order= %s",userId, JsonHelper.JsonFromObject(order)));
        Order orderSubmitted = service.store(order);
        log.warn(String.format("end orderid= %s",orderSubmitted.getId()));
        log.debug(String.format("end order= %s",JsonHelper.JsonFromObject(orderSubmitted)));
        return ResponseEntity.ok(orderSubmitted);
    }

    @GetMapping("/view")
    public List<Order> read(@RequestHeader(name = "userId", required = true) String userId) throws JsonProcessingException {
        log.warn("start");
        log.debug(String.format("start userid= %s",userId));
        log.warn("end");
        List<Order> orders= service.getAll();
        log.debug(String.format("end return= %s",JsonHelper.JsonFromObject(orders)));
        return orders;
    }

    @PutMapping("/replace/{id}")
    public @ResponseBody List<Order> update(@RequestHeader(name = "userId", required = true) String userId,@PathVariable(value = "id") Integer orderId,@RequestBody @Valid Order order) throws JsonProcessingException {
        log.warn(String.format("start id= %s",orderId.toString()));
        log.debug(String.format("start userid= %s,id= %s,order= %s",userId, orderId.toString(), JsonHelper.JsonFromObject(order)));
        service.store(orderId,order);
        List<Order> orders= service.getAll();
        log.warn("end");
        log.debug(String.format("end return= %s",JsonHelper.JsonFromObject(orders)));
        return orders;
    }

    @DeleteMapping("/remove/{id}")
    public List<Order> delete(@RequestHeader(name = "userId", required = true) String userId,@PathVariable(value = "id") Integer orderId) throws JsonProcessingException {
        log.warn(String.format("start id= %s",orderId.toString())); // debug will do when warn run
        service.remove(orderId);
        List<Order> orders= service.getAll();
        log.warn("end");
        log.debug(String.format("end return= %s",JsonHelper.JsonFromObject(orders)));
        return orders;
    }
}
