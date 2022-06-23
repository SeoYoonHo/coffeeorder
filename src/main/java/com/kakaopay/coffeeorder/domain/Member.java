package com.kakaopay.coffeeorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kakaopay.coffeeorder.exception.NotEnoughPointException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private int point;

	public Member chargePoint(int point) {

		if (point < 0) {
			throw new IllegalArgumentException("양수만 입력해주세요");
		}

		this.point += point;

		return this;
	}

	public void pointDeduction(int price) {

		if (this.point < price) {
			throw new NotEnoughPointException("포인트가 부족합니다.");
		}

		this.point -= price;

	}

}
