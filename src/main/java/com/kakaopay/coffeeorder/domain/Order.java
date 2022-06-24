package com.kakaopay.coffeeorder.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderMenu> orderMenuList = new ArrayList<>();
	
	private LocalDate orderDate;
	
	public void addOrderMenu(OrderMenu orderMenu) {
		orderMenuList.add(orderMenu);
		orderMenu.setOrder(this);
	}
	
	public static Order createOrder(Member member, OrderMenu... orderMenus) {
		Order order = new Order();
		order.setMember(member);
		order.setOrderDate(LocalDate.now());
		
		for(OrderMenu orderMenu : orderMenus) {
			order.addOrderMenu(orderMenu);
		}
		
		// 포인트 차감
		member.pointDeduction(order.getTotalPrice());
		
		return order;
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		for(OrderMenu orderMenu : orderMenuList) {
			totalPrice += orderMenu.getTotalPrice();
		}
		return totalPrice;
	}
	
	public int getTotalCount() {
		int totalCount = 0 ;
		for(OrderMenu orderMenu : orderMenuList) {
			totalCount += orderMenu.getCount();
		}
		return totalCount;
	}

}
