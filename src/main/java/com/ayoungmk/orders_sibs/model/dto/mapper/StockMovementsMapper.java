package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.StockMovementsDTO;
import com.ayoungmk.orders_sibs.model.entity.StockMovements;

@Component
public class StockMovementsMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public StockMovements toEntity(StockMovementsDTO dto) {
		StockMovements entity = mapper.map(dto, StockMovements.class);
		return entity;
	}
	
	public StockMovementsDTO toDTO(StockMovements entity) {
		StockMovementsDTO dto = mapper.map(entity, StockMovementsDTO.class);
		return dto;
	}
	
	public List<StockMovementsDTO> toDTO(List<StockMovements> stockMovements){
		return stockMovements.stream()
				.map(stockMovement -> toDTO(stockMovement))
				.collect(Collectors.toList());
	}
	
}
