package com.backendDevelopment.withtest.dbrestservice.repositories;

import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
