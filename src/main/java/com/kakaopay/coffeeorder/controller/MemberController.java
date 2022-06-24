package com.kakaopay.coffeeorder.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/api/v1/chargePoint")
	public ChargeMemberResponse chargePoint(@RequestBody @Valid ChargePointRequest request) {
		Member member = memberService.charePoint(request.getMember_id(), request.getPoint());
		return new ChargeMemberResponse(member.getId(), member.getPoint());
	}

	@Data
	static class ChargePointRequest {
		private Long member_id;
		private int point;
	}
	
	@Data
	@AllArgsConstructor
	static class ChargeMemberResponse{
		private Long member_id;
		private int point;
	}

}
