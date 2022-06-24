package com.kakaopay.coffeeorder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 포인트 충전
	public Member charePoint(Long id, int point) {
		Member member = memberRepository.findOne(id);
		member.chargePoint(point);
		memberRepository.save(member);
		return member;
	}

}
