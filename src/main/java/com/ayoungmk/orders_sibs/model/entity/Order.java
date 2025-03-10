package com.ayoungmk.orders_sibs.model.entity;

import com.ayoungmk.orders_sibs.model.enums.StatusOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity(name = "TB_ORDER")
@Getter
@Setter
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Timestamp creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId",referencedColumnName = "id")
	private Item itemId;
	
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",referencedColumnName = "id")
	private User userId;


	private String status;
}
