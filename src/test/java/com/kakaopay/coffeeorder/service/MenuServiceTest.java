package com.kakaopay.coffeeorder.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.DateOrderCount;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.dto.MostMenuDTO;
import com.kakaopay.coffeeorder.repository.DateOrderCountRepository;
import com.kakaopay.coffeeorder.repository.MenuRepository;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class MenuServiceTest {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	DateOrderCountRepository dateOrderCountRepository;
	
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
	
	@Test
	public void 인기메뉴조회() {

		LocalDate now = LocalDate.now();
		LocalDate weeksAgo = now.minusDays(7);

		Menu menu1 = new Menu();
		menu1.setName("testMenu1");
		menu1.setPrice(2000);

		Menu menu2 = new Menu();
		menu2.setName("testMenu1");
		menu1.setPrice(3000);

		Menu menu3 = new Menu();
		menu3.setName("testMenu1");
		menu1.setPrice(4000);

		Menu menu4 = new Menu();
		menu4.setName("testMenu1");
		menu1.setPrice(5000);

		DateOrderCount dateOrderCount1 = DateOrderCount.createDateOrderCount(menu1, now.minusDays(4), 1);
		DateOrderCount dateOrderCount2 = DateOrderCount.createDateOrderCount(menu2, now.minusDays(1), 11);
		DateOrderCount dateOrderCount3 = DateOrderCount.createDateOrderCount(menu3, now.minusDays(2), 5);
		DateOrderCount dateOrderCount4 = DateOrderCount.createDateOrderCount(menu4, now.minusDays(5), 7);

		// when
		dateOrderCountRepository.save(dateOrderCount1);
		dateOrderCountRepository.save(dateOrderCount2);
		dateOrderCountRepository.save(dateOrderCount3);
		dateOrderCountRepository.save(dateOrderCount4);
		
		List<MostMenuDTO> popularMenuList = menuService.findPopluarMenus();
		
		log.info("List!!!!!!!!!!");
		log.info(popularMenuList.toString());
		
		assertNotNull(popularMenuList);
		assertEquals(11, popularMenuList.get(0).getTotalCnt());
		assertEquals(7, popularMenuList.get(1).getTotalCnt());
		assertEquals(5, popularMenuList.get(2).getTotalCnt());		
	}

}
