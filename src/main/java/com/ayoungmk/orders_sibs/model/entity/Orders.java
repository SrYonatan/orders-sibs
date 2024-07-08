package com.ayoungmk.orders_sibs.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
	private Long itemId;
	
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private Long userId;
	
	private String status;
}
