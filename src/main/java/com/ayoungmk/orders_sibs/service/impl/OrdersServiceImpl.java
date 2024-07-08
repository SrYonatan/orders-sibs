package com.ayoungmk.orders_sibs.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import com.ayoungmk.orders_sibs.model.dto.OrderDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.OrderMapper;
import com.ayoungmk.orders_sibs.model.entity.Item;
import com.ayoungmk.orders_sibs.model.entity.StockMovement;
import com.ayoungmk.orders_sibs.model.entity.User;
import com.ayoungmk.orders_sibs.model.enums.StatusOrder;
import com.ayoungmk.orders_sibs.repository.ItemRepository;
import com.ayoungmk.orders_sibs.repository.StockMovementsRepository;
import com.ayoungmk.orders_sibs.repository.UsersRepository;
import com.ayoungmk.orders_sibs.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.model.entity.Order;
import com.ayoungmk.orders_sibs.model.entity.Stock;
import com.ayoungmk.orders_sibs.repository.OrdersRepository;
import com.ayoungmk.orders_sibs.repository.StockRepository;
import com.ayoungmk.orders_sibs.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrderMapper ordersMapper;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockMovementsRepository stockMovementsServiceImpl;

	private EmailService emailServiceImpl;


	@Autowired
	private ItemRepository itemRepository;


	public List<OrderDTO> findAll(){
		return ordersMapper.toDTO( ordersRepository.findAll());
	}

	public OrderDTO findById(Long id) throws OrdersNotFoundException{
		Optional<Order> order = ordersRepository.findById(id);
		if(order.isPresent()) {
			return ordersMapper.toDTO(order.get());
		}else {
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}


	public void deleteById(Long id) throws OrdersNotFoundException {
		ordersRepository.deleteById(id);
	}

	public OrderDTO updateOrders(Long id, OrderDTO orderDtoDetails) throws OrdersNotFoundException{
		Optional<Order> orderOpt = ordersRepository.findById(id);
		if(orderOpt.isPresent()) {
			Order order = orderOpt.get();
			order.getItemId().setName(orderDtoDetails.getItemName());
			order.setQuantity(orderDtoDetails.getQuantity());
			ordersRepository.save(order);
			return orderDtoDetails;
		}else {
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}

	public OrderDTO createOrder(OrderDTO orderDto) {
		Item item = itemRepository.findByName(orderDto.getItemName());
		Stock stock = stockRepository.findByItem(item);
		User user = usersRepository.findByName(orderDto.getUserName());
		Order order = new Order();
		order.setCreationDate(new Timestamp(System.currentTimeMillis()));
		order.setItemId(item);
		order.setUserId(user);
		order.setQuantity(orderDto.getQuantity());
		this.assignStatusOrder(order,stock,orderDto.getQuantity(),item);
		ordersRepository.save(order);
		return orderDto;
	}

	private void assignStatusOrder(Order order, Stock stock,Long quantity,Item item) {
		if (quantity <= stock.getQuantity()) {
			stockMovementsServiceImpl.save(StockMovement.builder().item(item).quantity(-order.getQuantity()).build());
			stock.setQuantity(stock.getQuantity() - order.getQuantity());
			stockRepository.save(stock);
			//configurar para fazer o send correto
			emailServiceImpl.sendSimpleMessage("to", "subject", "String text");
			order.setStatus(StatusOrder.COMPLETE.toString());
		} else {
			order.setStatus(StatusOrder.INCOMPLETE.toString());
		}
	}


}
