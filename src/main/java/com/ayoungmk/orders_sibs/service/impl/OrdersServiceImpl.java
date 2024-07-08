package com.ayoungmk.orders_sibs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.OrdersDTO;
import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.model.dto.StockMovementsDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.OrdersMapper;
import com.ayoungmk.orders_sibs.model.entity.Itens;
import com.ayoungmk.orders_sibs.model.entity.Orders;
import com.ayoungmk.orders_sibs.model.entity.Stock;
import com.ayoungmk.orders_sibs.repository.ItensRepository;
import com.ayoungmk.orders_sibs.repository.OrdersRepository;
import com.ayoungmk.orders_sibs.repository.StockRepository;
import com.ayoungmk.orders_sibs.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private StockServiceImpl stockServiceImpl;
	@Autowired
	private StockMovementsServiceImpl stockMovementsServiceImpl;
	
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ItensRepository itensRepository;

	
	public List<OrdersDTO> findAll(){
		List<Orders> orders = ordersRepository.findAll();
		List<OrdersDTO> ordersDto = ordersMapper.toDTO(orders);
		return ordersDto;
	}
	
	public OrdersDTO findById(Long id) throws OrdersNotFoundException{
		Optional<Orders> order = ordersRepository.findById(id);
		if(order.isPresent()) {
			OrdersDTO orderDto = ordersMapper.toDTO(order.get());
			return orderDto;
		}else {
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}
	
	public OrdersDTO save(OrdersDTO orderDto) {
		Orders order = ordersMapper.toEntity(orderDto);
		Orders createdorder = ordersRepository.save(order);
		OrdersDTO orderResponseDto = ordersMapper.toDTO(createdorder);
		return orderResponseDto;
	}
	
	public void deleteById(Long id) throws OrdersNotFoundException {
		Optional<Orders> orderOpt = ordersRepository.findById(id);
		if(orderOpt.isPresent()) {
			Orders order = orderOpt.get();
			ordersRepository.delete(order);
		}else {
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}

	public OrdersDTO updateOrders(Long id, OrdersDTO orderDtoDetails) throws OrdersNotFoundException{
		Optional<Orders> orderOpt = ordersRepository.findById(id);
		if(orderOpt.isPresent()) {
			Orders order = orderOpt.get();
			order.setItem(orderDtoDetails.getItem());
			order.setQuantity(orderDtoDetails.getQuantity());
			order.setUser(orderDtoDetails.getUser());
			order.setStatus(orderDtoDetails.getStatus());
			ordersRepository.save(order);
			OrdersDTO orderDto = ordersMapper.toDTO(order);
			return orderDto;
		}else {
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}
	
	public OrdersDTO createOrder(OrdersDTO orderDto) {
		Itens item = itensRepository.findByName(orderDto.getItemName());
	
		Stock stock = stockRepository.findByItem(item.getId());
		
		order.setStatus("Incomplete");
		order.setCreationDate("21/06/2024"); //Criar método pra pegar o current timestamp
	
		this.verifyStock(order, orderDto, stock);
		if ("Complete".equals(order.getStatus())) { //when an order is created, it should try to satisfy it with the current stock. ?
			StockMovementsDTO stockMovementDto = new StockMovementsDTO("21/06/2024", order.getItem(), (-order.getQuantity()));
			stockMovementsServiceImpl.save(stockMovementDto); //criar stock movement
			
			
			StockDTO stockDtoDetails = new StockDTO(order.getItem(), this.newQuantity(stockDto, order));
			stockServiceImpl.updateStock(stockServiceImpl.findIdbyItens(order.getItem()), stockDtoDetails);             //Update stock
			
			
			this.sendEmail(); //mandar email
		}
		return save(orderDto);
	}


	private Integer newQuantity(List<StockDTO> stockDto, Orders order) {
		Integer stockQuantity = stockDto.get(Math.toIntExact(stockServiceImpl.findIdbyItens(order.getItem()))).getQuantity();
		Integer newQuantity = stockQuantity - order.getQuantity();
		return newQuantity;
	}

	private void verifyStock(Orders order, OrdersDTO orderDto, List<Stock> stock) {
		if (orderDto.getQuantity() <= stock.get(0).getQuantity()) {
			order.setStatus("Complete");
		}
		
	}
	
	private void sendEmail() {
		// TODO Auto-generated method stub
		
	}
}
