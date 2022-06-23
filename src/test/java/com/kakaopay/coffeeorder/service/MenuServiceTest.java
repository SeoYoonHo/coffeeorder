package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.repository.MenuRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceTest {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuRepository menuRepository;

	@Test
	public void 메뉴등록() {
		//given
		Menu menu = new Menu();
		menu.setName("아이스 아메리카노");
		menu.setPrice(3000);
		
		//when
		Long addMenu_id = menuService.addMenu(menu);
		
		//then
		assertEquals(menu, menuRepository.findOne(addMenu_id));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복체크() {
		//given
		Menu menu1 = new Menu();
		menu1.setName("중복체크");
		menu1.setPrice(1000);
		
		Menu menu2 = new Menu();
		menu2.setName("중복체크");
		menu2.setPrice(2000);
		
		//when
		menuService.addMenu(menu1);
		menuService.addMenu(menu2); //예외 발생지점
		
		//then
		fail("예외 발생해야함");
	}
	
	@Test
	public void 메뉴조회() {
		//given
		Menu menu = new Menu();
		menu.setName("아이스 아메리카노");
		menu.setPrice(3000);
		
		//when
		menuService.addMenu(menu);
		List<Menu> menuList = menuService.findAllMenus();
		
		//then
		assertTrue(menuList instanceof List<?> && menuList.size() > 0);
		
	}

}
