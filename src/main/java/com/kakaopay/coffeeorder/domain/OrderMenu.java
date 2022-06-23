package com.kakaopay.coffeeorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderMenu {
	
	@Id @GeneratedValue
	@Column(name = "order_menu_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	

}
