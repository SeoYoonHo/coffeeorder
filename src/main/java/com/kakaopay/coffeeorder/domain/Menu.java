package com.kakaopay.coffeeorder.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Menu {
	
	@Id  @GeneratedValue
	@Column(name = "menu_id")
	private Long id;
	
	private String name;
	
	private int price;

}
