package com.kakaopay.coffeeorder.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderMenu> orderMenuList = new ArrayList<>();
	
	public void addOrderMenu(OrderMenu orderMenu) {
		orderMenuList.add(orderMenu);
	}
	
	public static Order createOrder(Member member, OrderMenu... orderMenus) {
		Order order = new Order();
		order.setMember(member);
		for(OrderMenu orderMenu : orderMenus) {
			order.addOrderMenu(orderMenu);
		}
		
		return order;
	}

}
