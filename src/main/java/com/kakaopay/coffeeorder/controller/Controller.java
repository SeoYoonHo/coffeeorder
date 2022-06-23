package com.kakaopay.coffeeorder.controller;

import org.hibernate.mapping.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffeeorder")
public class Controller {
	
	@GetMapping(value = "/list")
	public Map coffeeMenuList(Model model) {
		return null;
	}
	
	@PostMapping(value= "/charge")
	public int chargePoint(Model model) {
		return 0;
	}
	
	@GetMapping(value = "/bestlist")
	public Map coffeeMenuBestList(Model model) {
		return null;
	}
	
	@PostMapping(value= "/order")
	public int orderCoffee(Model model) {
		return 0;
	}
	
}
