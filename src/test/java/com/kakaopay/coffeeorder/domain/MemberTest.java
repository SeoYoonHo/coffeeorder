package com.kakaopay.coffeeorder.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kakaopay.coffeeorder.exception.NotEnoughPointException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {

	@Test
	public void 포인트추가() {
		// given
		Member member = new Member();
		int chargePoint = 3000;

		// when
		member.chargePoint(chargePoint);

		// then
		assertEquals(chargePoint, member.getPoint());
	}

	@Test
	public void 포인트감소() {
		// given
		Member member = new Member();
		int initialPoint = 5000;
		member.setPoint(initialPoint);
		int menuPrice = 3000;

		// when
		member.pointDeduction(menuPrice);

		// then
		assertEquals(initialPoint - menuPrice, member.getPoint());
	}
	
	@Test(expected = NotEnoughPointException.class)
	public void 포인트부족() {
		// given
		Member member = new Member();
		int initialPoint = 2000;
		member.setPoint(initialPoint);
		int menuPrice = 3000;

		// when
		member.pointDeduction(menuPrice);

		// then
		fail("exception 발생해야함");
	}

}
