package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.StockMovementsNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockMovementsDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.StockMovementsMapper;
import com.ayoungmk.orders_sibs.model.entity.StockMovements;
import com.ayoungmk.orders_sibs.repository.StockMovementsRepository;
import com.ayoungmk.orders_sibs.service.StockMovementsService;

@Service
public class StockMovementsServiceImpl implements StockMovementsService {
	
	@Autowired
	private StockMovementsRepository stockMovementsRepository;
	@Autowired
	private StockMovementsMapper stockMovementsMapper;
	
	public List<StockMovementsDTO> findAll(){
		List<StockMovements> stockMovements = stockMovementsRepository.findAll();
		List<StockMovementsDTO> stockMovementsDto = stockMovementsMapper.toDTO(stockMovements);
		return stockMovementsDto;
	}
	
	public StockMovementsDTO findById(Long id) throws StockMovementsNotFoundException {
		Optional<StockMovements> stockMovements = stockMovementsRepository.findById(id);
		if(stockMovements.isPresent()) {
			StockMovementsDTO stockMovementDto = stockMovementsMapper.toDTO(stockMovements.get());
			return stockMovementDto;
		}else {
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}
	
	public StockMovementsDTO save(StockMovementsDTO stockMovementDto) {
//		when a stock movement is created, the system should try to attribute it to an order that isn't complete
		StockMovements stockMovement = stockMovementsMapper.toEntity(stockMovementDto);
		StockMovements createdstockMovement = stockMovementsRepository.save(stockMovement);
		StockMovementsDTO stockMovementResponseDto = stockMovementsMapper.toDTO(createdstockMovement);
		return stockMovementResponseDto;
	}
	
	public void deleteById(Long id) throws StockMovementsNotFoundException {
		Optional<StockMovements> stockMovementsOpt = stockMovementsRepository.findById(id);
		if(stockMovementsOpt.isPresent()) {
			StockMovements stockMovement = stockMovementsOpt.get();
			stockMovementsRepository.delete(stockMovement);
		}else {
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}

	public StockMovementsDTO updateStockMovements(Long id, StockMovementsDTO stockMovementsDtoDetails) throws StockMovementsNotFoundException {
		Optional<StockMovements> stockMovementsOpt = stockMovementsRepository.findById(id);
		if(stockMovementsOpt.isPresent()) {
			StockMovements stockMovement = stockMovementsOpt.get();
			stockMovement.setItem(stockMovementsDtoDetails.getItem());
			stockMovement.setQuantity(stockMovementsDtoDetails.getQuantity());
			stockMovementsRepository.save(stockMovement);
			StockMovementsDTO stockMovementDto = stockMovementsMapper.toDTO(stockMovement);
			return stockMovementDto;
		}else {
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}
}
