package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
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
		//given
		Member member = new Member();
		
		//when
		member.chargePoint(3000);
		memberRepository.save(member);
		
		//then
		Member findMember = memberRepository.findOne(member.getId());
		assertTrue(findMember.getPoint() == 3000);
	}

}

