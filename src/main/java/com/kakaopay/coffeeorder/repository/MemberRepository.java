package com.kakaopay.coffeeorder.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.exception.NoSuchException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		Member member = em.find(Member.class, id);
		if(member == null) {
			throw new NoSuchException("해당 아이디를 가진 회원을 찾을 수 없습니다.");
		}
		return em.find(Member.class, id);
	}
}
