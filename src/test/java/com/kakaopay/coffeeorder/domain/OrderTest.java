package com.kakaopay.coffeeorder.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

	@Test
	public void 주문생성() {
		// given
		Member member = new Member();
		member.setPoint(10000);

		Menu menu1 = new Menu();
		int initialPrice1 = 2000;
		int initialCount1 = 1;
		
		Menu menu2 = new Menu();
		int initialPrice2 = 3000;
		int initialCount2 = 1;

		OrderMenu orderMenu1 = OrderMenu.createOrderMenu(menu1, initialPrice1, initialCount1);
		OrderMenu orderMenu2 = OrderMenu.createOrderMenu(menu2, initialPrice2, initialCount2);
		
		List<OrderMenu> orderMenuList = new ArrayList<>();
		orderMenuList.add(orderMenu1);
		orderMenuList.add(orderMenu2);
		
		// when
		Order order = Order.createOrder(member, orderMenu1, orderMenu2);

		// then
		assertEquals("주문한 회원이 정확해야한다.", member.getId(), order.getMember().getId());
		assertNotNull(order.getOrderMenuList());
		assertArrayEquals("주문아이템 리스트가 정확해야한다.", orderMenuList.toArray(), order.getOrderMenuList().toArray());

	}

	@Test
	public void 총액가져오기() {
		// given
		Member member = new Member();
		member.setPoint(10000);

		Menu menu1 = new Menu();
		int initialPrice1 = 2000;
		int initialCount1 = 1;
		
		Menu menu2 = new Menu();
		int initialPrice2 = 3000;
		int initialCount2 = 1;

		OrderMenu orderMenu1 = OrderMenu.createOrderMenu(menu1, initialPrice1, initialCount1);
		OrderMenu orderMenu2 = OrderMenu.createOrderMenu(menu2, initialPrice2, initialCount2);
		
		// when
		Order order = Order.createOrder(member, orderMenu1, orderMenu2);

		// then
		assertEquals(initialPrice1 + initialPrice2, order.getTotalPrice());
	}

	@Test
	public void 총건수가져오기() {
		// given
		Member member = new Member();
		member.setPoint(10000);

		Menu menu1 = new Menu();
		int initialPrice1 = 2000;
		int initialCount1 = 1;
		
		Menu menu2 = new Menu();
		int initialPrice2 = 3000;
		int initialCount2 = 1;

		OrderMenu orderMenu1 = OrderMenu.createOrderMenu(menu1, initialPrice1, initialCount1);
		OrderMenu orderMenu2 = OrderMenu.createOrderMenu(menu2, initialPrice2, initialCount2);
		
		// when
		Order order = Order.createOrder(member, orderMenu1, orderMenu2);

		// then
		assertEquals(initialCount1 + initialCount2, order.getTotalCount());
	}

}
