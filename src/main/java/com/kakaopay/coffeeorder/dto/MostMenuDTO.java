package com.kakaopay.coffeeorder.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MostMenuDTO {
	private Long id;
	private String name;
	private Integer price;
	private Long totalCnt;
	
	public MostMenuDTO(Long id, Long totalCnt) {
		this.id = id;
		this.totalCnt = totalCnt;
	}
}