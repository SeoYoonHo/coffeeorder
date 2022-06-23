package com.kakaopay.coffeeorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	private int orderPrice;
	private int count;
	
	public static OrderMenu createOrderMenu(Menu menu, int orderPrice, int count) {
		OrderMenu orderMenu = new OrderMenu();
		
		orderMenu.setMenu(menu);
		orderMenu.setOrderPrice(orderPrice);
		orderMenu.setCount(count);
		
		return orderMenu;
	}
	
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

}
