package com.kakaopay.coffeeorder.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMenuTest {

	@Test
	public void 주문메뉴생성() {
		//given
		Menu menu = new Menu();
		int initialPrice = 3000;
		int initialCount = 1;
		
		//when
		OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, initialPrice, initialCount);
		
		//then
		assertEquals("메뉴가 정확히 입려되어야 한다.", menu, orderMenu.getMenu());
		assertEquals("가격이 정확히 입려되어야 한다.", initialPrice, orderMenu.getOrderPrice());
		assertEquals("건수가 정확히 입려되어야 한다.", initialCount, orderMenu.getCount());
	}
	
	@Test
	public void 주문메뉴총금액() {
		//given
		Menu menu = new Menu();
		int initialPrice = 3000;
		int initialCount = 2;
		
		//when
		OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, initialPrice, initialCount);
		
		//then
		assertEquals("총 금액은 건수 * 가격이다.", initialPrice * initialCount, orderMenu.getTotalPrice());
	}

}
