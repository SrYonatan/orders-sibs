package com.ayoungmk.orders_sibs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

	private String itemName;
	private Long quantity;
	private String userName;

}
