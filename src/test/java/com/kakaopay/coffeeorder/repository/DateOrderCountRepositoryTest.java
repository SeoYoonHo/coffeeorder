package com.kakaopay.coffeeorder.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.DateOrderCount;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.dto.MostMenuDTO;

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
	public void 날짜기준모스트메뉴리스트() {
		// given
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

		List<MostMenuDTO> dateOrderList = dateOrderCountRepository.findMostCntMenuByDate(weeksAgo);

		// then
		assertNotNull(dateOrderList);
		
		int index = 0;
		Long prevCnt = (long) 0;
		
		for(MostMenuDTO mostMenuDto : dateOrderList) {
			if(index == 0) {
				prevCnt = mostMenuDto.getTotalCnt();
				index++;
				continue;
			}
			
			if(prevCnt < mostMenuDto.getTotalCnt()) {
				fail("정렬 제대로 안됨");
				break;
			}
			
			prevCnt = mostMenuDto.getTotalCnt();
			index++;
		}
		
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
