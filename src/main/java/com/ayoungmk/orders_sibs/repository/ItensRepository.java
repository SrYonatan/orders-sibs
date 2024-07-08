package com.ayoungmk.orders_sibs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.Itens;

public interface ItensRepository extends JpaRepository<Itens, Long>{
	
	Itens findByName(String name);
}
