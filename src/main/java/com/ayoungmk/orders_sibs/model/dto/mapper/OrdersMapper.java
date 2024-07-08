package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.OrdersDTO;
import com.ayoungmk.orders_sibs.model.entity.Order;

@Component
public class OrdersMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Order toEntity(OrdersDTO dto) {
		Order entity = mapper.map(dto, Order.class);
		return entity;
	}
	
	public OrdersDTO toDTO(Order entity) {
		OrdersDTO dto = mapper.map(entity, OrdersDTO.class);
		return dto;
	}
	
	public List<OrdersDTO> toDTO(List<Order> orders){
		return orders.stream()
				.map(order -> toDTO(order))
				.collect(Collectors.toList());
	}
	
}
