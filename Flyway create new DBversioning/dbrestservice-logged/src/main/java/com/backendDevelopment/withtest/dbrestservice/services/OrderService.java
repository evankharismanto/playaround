package com.backendDevelopment.withtest.dbrestservice.services;

import com.backendDevelopment.withtest.dbrestservice.helper.JsonHelper;
import com.backendDevelopment.withtest.dbrestservice.repositories.*;
import com.backendDevelopment.withtest.dbrestservice.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order store(Order order) throws JsonProcessingException {
        log.warn(String.format("start date= %s customerName= %s",order.getDate().toString(),order.getCustomer().getName()));
        log.debug(String.format("start order= %s",JsonHelper.JsonFromObject(order)));
        var orderSubmitted = orderRepository.save(order);
        log.warn(String.format("end orderid= %s",orderSubmitted.getId()));
        log.debug(String.format("end order= %s",JsonHelper.JsonFromObject(orderSubmitted)));
        return orderSubmitted;
    }
    public List<Order> getAll() throws JsonProcessingException {
        log.warn("start");// debug will show warn
        var orders = orderRepository.findAll();
        log.warn("end");
        log.debug(String.format("end return= %s",JsonHelper.JsonFromObject(orders)));
        return orders;
    }
    public void remove(Integer id){
        log.warn(String.format("start id= %s",id.toString())); // debug will show warn
        orderRepository.deleteById(id);
        log.warn("end"); // debug will show warn
    }
    public Order store(Integer id, Order order) throws JsonProcessingException {
        log.warn(String.format("start id= %s",id.toString()));
        log.debug(String.format("start id= %s,order= %s", id.toString(), JsonHelper.JsonFromObject(order)));
        Order entityOrder= orderRepository.findById(id).get();
        storingOrderDetails(entityOrder,order);
        var orderSubmitted = orderRepository.findById(id).get();
        log.warn("end");
        log.debug(String.format("end return= %s",JsonHelper.JsonFromObject(orderSubmitted)));
        return orderSubmitted;
    }

    private void storingOrderDetails(Order existingOrder,Order newOrder){
        existingOrder.setDate(newOrder.getDate());
        existingOrder.setDelivery(newOrder.getDelivery());
        existingOrder.setAddress(newOrder.getAddress());
        existingOrder.setCustomer(newOrder.getCustomer());
        existingOrder.setItems(newOrder.getItems());
        orderRepository.save(existingOrder);
    }
}
