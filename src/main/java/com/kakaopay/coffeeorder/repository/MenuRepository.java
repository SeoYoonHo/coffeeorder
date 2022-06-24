package com.kakaopay.coffeeorder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.exception.NoSuchException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

	private final EntityManager em;

	public void save(Menu menu) {
		em.persist(menu);
	}

	public Menu findOne(Long id) {
		Menu menu = em.find(Menu.class, id);
		if (menu == null) {
			throw new NoSuchException("해당 아이디를 가진 메뉴를 찾을 수 없습니다.");
		}
		return menu;
	}

	public List<Menu> findAll() {
		return em.createQuery("select m from Menu m", Menu.class).getResultList();
	}

}
