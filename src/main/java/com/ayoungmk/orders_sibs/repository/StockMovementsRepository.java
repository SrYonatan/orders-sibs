package com.ayoungmk.orders_sibs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.StockMovements;

public interface StockMovementsRepository extends JpaRepository<StockMovements, Long>{

}
