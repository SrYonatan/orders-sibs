package com.ayoungmk.orders_sibs.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity(name = "TB_STOCK_MOVEMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Timestamp creationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name = "item_id")
	private Item item;

	private Long quantity;
	
	
}
