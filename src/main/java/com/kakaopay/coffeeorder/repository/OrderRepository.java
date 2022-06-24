package com.kakaopay.coffeeorder.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.kakaopay.coffeeorder.domain.Order;
import com.kakaopay.coffeeorder.exception.NoSuchException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		Order order = em.find(Order.class, id);
		if (order == null) {
			throw new NoSuchException("해당 아이디를 가진 주문을 찾을 수 없습니다.");
		}
		return order;
	}

}
