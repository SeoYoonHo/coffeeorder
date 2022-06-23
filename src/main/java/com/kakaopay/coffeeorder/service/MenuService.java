package com.kakaopay.coffeeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	@Transactional
	public Long addMenu(Menu menu) {
		validateDuplicateMenu(menu);
		menuRepository.save(menu);
		return menu.getId();
	}

	private void validateDuplicateMenu(Menu menu) {
		List<Menu> menuList = menuRepository.findByNames(menu.getName());
		if (menuList.size() > 0) {
			throw new IllegalStateException("이미 존재하는 메뉴입니다.");
		}

	}

	public List<Menu> findAllMenus() {
		return menuRepository.findAll();
	}
}
