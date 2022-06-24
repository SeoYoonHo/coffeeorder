package com.kakaopay.coffeeorder.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.domain.Order;
import com.kakaopay.coffeeorder.domain.OrderMenu;
import com.kakaopay.coffeeorder.exception.NoSuchException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	EntityManager em;

	@Test
	public void 저장() {
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
		orderRepository.save(order);

		// then
		assertEquals(order, orderRepository.findOne(order.getId()));
	}
	
	@Test(expected = NoSuchException.class)
	public void 없는주문찾기() {
		// given
		Long id = (long)-1;

		// when
		orderRepository.findOne(id);

		// then
		fail("에러 나야함");		
	}
}
