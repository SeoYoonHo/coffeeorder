package com.kakaopay.coffeeorder.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.service.MenuService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuController {
	
	private final MenuService menuService;
	
	@GetMapping("api/v1/menus")
	public Result<List<MenuDto>> allMenus() {
		
		List<Menu> menus = menuService.findAllMenus();
		
		List<MenuDto> collect = menus.stream()
				.map(m -> new MenuDto(m.getName(), m.getPrice()))
				.collect(Collectors.toList());
		
		return new Result<List<MenuDto>>(collect);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	static class MenuDto {
		private String name;
		private int price;
	}
	

}
