package com.ayoungmk.orders_sibs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.Order;

public interface OrdersRepository extends JpaRepository<Order, Long>{

}
