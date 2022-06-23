package com.kakaopay.coffeeorder.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "menu_id", "date" }))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class DateOrderCount {
	
	@Id @GeneratedValue
	@Column(name = "date_order_count_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	private LocalDate date;

	private int orderCount;
	
	public void addOrderCount(int orderCount) {
		if(orderCount < 0) {
			throw new IllegalArgumentException("주문수량은 양수만 입력할 수 있습니다.");
		}
		
		this.orderCount += orderCount;
	}

	
	public static DateOrderCount createDateOrderCount(Menu menu, LocalDate date, int orderCount) {
		DateOrderCount dateOrderCount = new DateOrderCount();
		dateOrderCount.setMenu(menu);
		dateOrderCount.setDate(date);
		dateOrderCount.setOrderCount(orderCount);
		
		return dateOrderCount;
	}
}
