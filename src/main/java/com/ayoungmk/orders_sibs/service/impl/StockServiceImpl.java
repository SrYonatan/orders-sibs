package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import com.ayoungmk.orders_sibs.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.StockNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.StockMapper;
import com.ayoungmk.orders_sibs.model.entity.Stock;
import com.ayoungmk.orders_sibs.repository.StockRepository;
import com.ayoungmk.orders_sibs.service.StockService;

@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private StockMapper stockMapper;
	
	public List<StockDTO> findAll(){
		List<Stock> stocks = stockRepository.findAll();
		List<StockDTO> stocksDto = stockMapper.toDTO(stocks);
		return stocksDto;
	}
	
	public StockDTO findById(Long id) throws StockNotFoundException {
		Optional<Stock> stock = stockRepository.findById(id);
		if(stock.isPresent()) {
			StockDTO stockDto = stockMapper.toDTO(stock.get());
			return stockDto;
		}else {
			throw new ItensNotFoundException("Stock with id " + id + " not found!");
		}
	}
	
	public StockDTO save(StockDTO stockDto) {
		Stock stock = stockMapper.toEntity(stockDto);
		Stock createdStock = stockRepository.save(stock);
		StockDTO stockResponseDto = stockMapper.toDTO(createdStock);
		return stockResponseDto;
	}
	
	public void deleteById(Long id) throws StockNotFoundException {
		Optional<Stock> stockOpt = stockRepository.findById(id);
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			stockRepository.delete(stock);
		}else {
			throw new ItensNotFoundException("Stock with id " + id + " not found!");
		}
	}

	@Override
	public Long findIdbyItens(Item item) {
		return null;
	}

	public StockDTO updateStock(Long id, StockDTO stockDtoDetails) throws StockNotFoundException {
		Optional<Stock> stockOpt = stockRepository.findById(id);
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
//			stock.setItem(stockDtoDetails.getItem());
			stock.setQuantity(stockDtoDetails.getQuantity());
			stockRepository.save(stock);
			StockDTO stockDto = stockMapper.toDTO(stock);
			return stockDto;
		}else {
			throw new ItensNotFoundException("Stock with id " + id + " not found!");
		}
	}

//	@Override
//	public Long findIdbyItens(Item item) {
//		return stockRepository.findIdbyIten(item);
//	}
//
}
