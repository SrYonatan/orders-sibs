package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.OrdersDTO;

public interface OrdersService {
	
	public List<OrdersDTO> findAll();
	public OrdersDTO findById(Long id) throws OrdersNotFoundException;
	public OrdersDTO save(OrdersDTO orderDto);
	public OrdersDTO updateOrders(Long id, OrdersDTO orderDtoDetails) throws OrdersNotFoundException;
	public void deleteById(Long id) throws OrdersNotFoundException;
}
