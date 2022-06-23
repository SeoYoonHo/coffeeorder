package com.kakaopay.coffeeorder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.domain.Order;
import com.kakaopay.coffeeorder.domain.OrderMenu;
import com.kakaopay.coffeeorder.exception.NotEnoughPointException;
import com.kakaopay.coffeeorder.repository.MemberRepository;
import com.kakaopay.coffeeorder.repository.MenuRepository;
import com.kakaopay.coffeeorder.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final MenuRepository menuRepository;
	
	@Transactional
	public Long order(Long memberId, Long menuId, int count) {
		
		//Entity 조회
		Member member = memberRepository.findOne(memberId);
		Menu menu = menuRepository.findOne(menuId);
		
		//검증
		int point = member.getPoint();
		int price = menu.getPrice();
		if (point < price * count) {
			throw new NotEnoughPointException("포인트가 부족합니다. 포인트를 충전해주세요");
		}
		
		//주문메뉴 생성
		OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, menu.getPrice(), count);
		
		//주문 생성
		Order order = Order.createOrder(member, orderMenu);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}

}
