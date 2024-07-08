package com.ayoungmk.orders_sibs.model.dto;



import com.ayoungmk.orders_sibs.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO {

	private Timestamp creationDate;
	private String itemName;
	private Long quantity;
}
