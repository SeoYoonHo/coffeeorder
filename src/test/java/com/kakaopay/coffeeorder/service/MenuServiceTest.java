package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertTrue;

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
	MenuRepository menuRepository;
	
	@Autowired
	MenuService menuService;
	
	@Test
	public void 메뉴조회() {
		//given
		Menu menu = new Menu();
		menu.setName("아이스 아메리카노");
		menu.setPrice(3000);
		
		//when
		menuRepository.save(menu);
		List<Menu> menuList = menuService.findAllMenus();
		
		//then
		assertTrue(menuList instanceof List<?> && menuList.size() > 0);
		
	}

}
