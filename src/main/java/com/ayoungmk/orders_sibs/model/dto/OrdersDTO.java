package com.ayoungmk.orders_sibs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
	
	private String creationDate;
	private String itemName;
	private Integer quantity;
	private String userName;
	private String status;
}
