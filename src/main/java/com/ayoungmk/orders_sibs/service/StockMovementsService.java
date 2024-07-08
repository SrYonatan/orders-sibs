package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.StockMovementsNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockMovementsDTO;

public interface StockMovementsService {
	
	public List<StockMovementsDTO> findAll();
	public StockMovementsDTO findById(Long id) throws StockMovementsNotFoundException;
	public StockMovementsDTO save(StockMovementsDTO stockMovementDto);
	public StockMovementsDTO updateStockMovements(Long id, StockMovementsDTO stockMovementDtoDetails) throws StockMovementsNotFoundException;
	public void deleteById(Long id) throws StockMovementsNotFoundException;
}
