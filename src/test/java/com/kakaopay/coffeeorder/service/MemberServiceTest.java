package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.exception.NotEnoughPointException;
import com.kakaopay.coffeeorder.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 포인트충전() {
		// given
		Member member = new Member();
		int initalPoint = 1000;
		int chargePoint = 3000;
		member.setPoint(initalPoint);

		// when
		member.chargePoint(chargePoint);
		memberRepository.save(member);

		// then
		Member findMember = memberRepository.findOne(member.getId());
		assertTrue(findMember.getPoint() == initalPoint + chargePoint);
	}

	@Test
	public void 포인트차감() {
		// given
		Member member = new Member();
		int initalPoint = 3000;
		int price = 2000;
		member.setPoint(initalPoint);

		// when
		member.pointDeduction(price);
		memberRepository.save(member);

		// then
		Member findMember = memberRepository.findOne(member.getId());
		assertTrue(findMember.getPoint() == initalPoint - price);
	}

	@Test(expected = NotEnoughPointException.class)
	public void 포인트부족() {
		// given
		Member member = new Member();
		int initalPoint = 6000;
		int price = 7000;

		// when
		member.chargePoint(initalPoint);
		member.pointDeduction(price);
		memberRepository.save(member);

		// then
		fail("금액부족 예외 발생해야함");
	}

}
