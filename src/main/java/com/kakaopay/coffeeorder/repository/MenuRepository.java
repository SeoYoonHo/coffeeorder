package com.kakaopay.coffeeorder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.kakaopay.coffeeorder.domain.Menu;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

	private final EntityManager em;

	public void save(Menu menu) {
		em.persist(menu);
	}
	
	public Menu findOne(Long id) {
		return em.find(Menu.class, id);
	}
	
	public List<Menu> findAll(){
		return em.createQuery("select m from Menu m", Menu.class)
				.getResultList();
	}

}
