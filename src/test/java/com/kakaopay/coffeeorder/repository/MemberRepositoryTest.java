package com.kakaopay.coffeeorder.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.exception.NoSuchException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	EntityManager em;

	@Test
	public void 저장() {
		// given
		Member member = new Member();

		// when
		memberRepository.save(member);
		em.flush();

		// then
		assertEquals(member, memberRepository.findOne(member.getId()));
	}
	
	@Test(expected = NoSuchException.class)
	public void 없는사람찾기() {
		// given
		Long id = (long)-1;

		// when
		memberRepository.findOne(id);

		// then
		fail("에러 나야함");		
	}

}
