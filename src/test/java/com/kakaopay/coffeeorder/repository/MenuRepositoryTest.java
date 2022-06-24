package com.kakaopay.coffeeorder.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuRepositoryTest {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	EntityManager em;

	@Test
	public void 저장() {
		// given
		Menu menu = new Menu();

		// when
		menuRepository.save(menu);
		em.flush();

		// then
		assertEquals(menu, menuRepository.findOne(menu.getId()));
	}
	
	@Test
	public void 모두조회() {
		// given
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
		Menu menu3 = new Menu();

		// when
		menuRepository.save(menu1);
		menuRepository.save(menu2);
		menuRepository.save(menu3);
		em.flush();
		
		// then
		List<Menu> menuList = menuRepository.findAll();
		assertNotNull(menuList);
		assertTrue(menuList.contains(menu1));
		assertTrue(menuList.contains(menu2));
		assertTrue(menuList.contains(menu3));
	}
}
