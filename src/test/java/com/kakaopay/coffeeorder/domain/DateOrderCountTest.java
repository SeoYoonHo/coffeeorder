package com.kakaopay.coffeeorder.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(readOnly = true)
public class DateOrderCountTest {
	
	@Test
	public void 주문수량추가() {
		//given
		DateOrderCount dateOrderCount = new DateOrderCount();
		int initalOrderCount = 1000;
		int addOrderCount = 2000;
		dateOrderCount.setOrderCount(initalOrderCount);
		
		//when
		dateOrderCount.addOrderCount(addOrderCount);
		
		//then
		assertEquals("총 주문수량이 추가된 수량만큼 증가되어야 함", initalOrderCount + addOrderCount, dateOrderCount.getOrderCount());
	}

}
