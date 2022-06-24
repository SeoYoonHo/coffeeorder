package com.kakaopay.coffeeorder.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.dto.MostMenuDTO;
import com.kakaopay.coffeeorder.repository.DateOrderCountRepository;
import com.kakaopay.coffeeorder.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;
	private final DateOrderCountRepository dateOrderCountRepository;

	public List<Menu> findAllMenus() {
		return menuRepository.findAll();
	}

	public List<MostMenuDTO> findPopluarMenus() {
		LocalDate weeksAgo = LocalDate.now().minusDays(7);
		List<MostMenuDTO> popularMenuList = new ArrayList<>();
		List<MostMenuDTO> orderedMostMenuList = dateOrderCountRepository.findMostCntMenuByDate(weeksAgo);

		int index = 0;
		for (MostMenuDTO mostMenuDTO : orderedMostMenuList) {
			if (index > 2)
				break;
			Menu menu = menuRepository.findOne(mostMenuDTO.getId());
			mostMenuDTO.setName(menu.getName());
			mostMenuDTO.setPrice(menu.getPrice());
			popularMenuList.add(mostMenuDTO);
			index++;
		}

		return popularMenuList;
	}
}
