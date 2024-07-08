package com.ayoungmk.orders_sibs.model.dto;



import com.ayoungmk.orders_sibs.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
	
	private String itemName;
	private Long quantity;
}
