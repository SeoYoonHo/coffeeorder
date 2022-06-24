package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.DateOrderCount;
import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.domain.Order;
import com.kakaopay.coffeeorder.exception.NotEnoughPointException;
import com.kakaopay.coffeeorder.repository.DateOrderCountRepository;
import com.kakaopay.coffeeorder.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	DateOrderCountRepository dateOrderCountRepository;

	@Autowired
	EntityManager em;

	@Test
	@Rollback(false)
	public void 상품주문() throws Exception {
		// given
		Member member = new Member();
		member.setPoint(6000);
		em.persist(member);

		Menu menu = new Menu();
		menu.setName("아이스 아메리카노");
		menu.setPrice(2500);
		em.persist(menu);

		DateOrderCount dateOrderCount = dateOrderCountRepository.findByMenuAndDate(menu.getId(), LocalDate.now());
		int initialMenuOrderCnt = dateOrderCount != null ? dateOrderCount.getOrderCount() : 0;

		int orderCount = 2;

		// when
		Order createOrder = orderService.order(member.getId(), menu.getId(), orderCount);

		// then
		Order getOrder = orderRepository.findOne(createOrder.getId());
		int orderAfterMenuCnt = dateOrderCountRepository.findByMenuAndDate(menu.getId(), LocalDate.now())
				.getOrderCount();
		
		System.out.println("initialMenuOrderCnt : " + initialMenuOrderCnt);
		System.out.println("orderAfterMenuCnt : " + orderAfterMenuCnt);

		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderMenuList().size());
		assertEquals("주문 가격은 가격 * 수량이다.", 2500 * orderCount, getOrder.getTotalPrice());
		assertEquals("주문 가격만큼 포인트에서 차감되어야 한다.", 6000 - 2500 * orderCount, getOrder.getMember().getPoint());
		assertEquals("주문한 메뉴건수가 날짜별 메뉴주문건수에 합계되어야 한다.", initialMenuOrderCnt + orderCount, orderAfterMenuCnt);

	}

	@Test(expected = NotEnoughPointException.class)
	public void 금액부족() throws Exception {
		// given
		Member member = new Member();
		member.setPoint(6000);
		em.persist(member);

		Menu menu = new Menu();
		menu.setName("아이스 아메리카노");
		menu.setPrice(2500);
		em.persist(menu);

		int orderCount = 3;

		// when
		orderService.order(member.getId(), menu.getId(), orderCount);

		// then
		fail("금액부족 예외 발생해야함");
	}

}
