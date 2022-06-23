package com.kakaopay.coffeeorder.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.DateOrderCount;
import com.kakaopay.coffeeorder.domain.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DateOrderCountRepositoryTest {

	@Autowired
	DateOrderCountRepository dateOrderCountRepository;
	
	@Autowired
	EntityManager em;

	@Test
	public void 저장() {
		// given
		Menu menu = new Menu();
		DateOrderCount dateOrderCount = DateOrderCount.createDateOrderCount(menu, LocalDate.now(), 1);

		// when
		dateOrderCountRepository.save(dateOrderCount);

		// then
		assertEquals(dateOrderCount, em.find(DateOrderCount.class, dateOrderCount.getId()));
	}

	@Test
	public void 날짜찾기() {
		// given
		Menu menu = new Menu();
		DateOrderCount dateOrderCount = DateOrderCount.createDateOrderCount(menu, LocalDate.now(), 1);

		// when
		dateOrderCountRepository.save(dateOrderCount);
		DateOrderCount findo = dateOrderCountRepository.findByMenuAndDate(menu.getId(), dateOrderCount.getDate());

		// then
		assertEquals(dateOrderCount, findo);
	}

	@Test
	public void 삭제() {
		// given
		Menu menu = new Menu();
		DateOrderCount dateOrderCount = DateOrderCount.createDateOrderCount(menu, LocalDate.now(), 1);
		dateOrderCountRepository.save(dateOrderCount);
		
		// when
		dateOrderCountRepository.deleteByMenuAndDate(menu.getId(), dateOrderCount.getDate());		
		DateOrderCount findDo = dateOrderCountRepository.findByMenuAndDate(menu.getId(), dateOrderCount.getDate());

		// then
		assertNull(findDo);
	}

}
