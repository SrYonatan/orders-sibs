package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.StockMovementsDTO;
import com.ayoungmk.orders_sibs.model.entity.StockMovement;

@Component
public class StockMovementsMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public StockMovement toEntity(StockMovementsDTO dto) {
		StockMovement entity = mapper.map(dto, StockMovement.class);
		return entity;
	}
	
	public StockMovementsDTO toDTO(StockMovement entity) {
		StockMovementsDTO dto = mapper.map(entity, StockMovementsDTO.class);
		return dto;
	}
	
	public List<StockMovementsDTO> toDTO(List<StockMovement> stockMovements){
		return stockMovements.stream()
				.map(stockMovement -> toDTO(stockMovement))
				.collect(Collectors.toList());
	}
	
}
