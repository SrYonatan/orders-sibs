package com.ayoungmk.orders_sibs.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StockMovements {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String creationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name = "item_id")
	private Itens item;
	private Integer quantity;
	
	
}
