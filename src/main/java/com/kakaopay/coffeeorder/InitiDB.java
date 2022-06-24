package com.kakaopay.coffeeorder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.Member;
import com.kakaopay.coffeeorder.domain.Menu;
import com.kakaopay.coffeeorder.service.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitiDB {
	
	private final InitService initService;
	
	@PostConstruct
	public void init() {
		initService.memberInit();
		initService.menuInit();
		initService.orderInit();
	}
	
	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		
		private final EntityManager em;
		private final OrderService orderService;
		
		public void memberInit() {
			
			Member member1 = new Member();
			member1.setPoint(100000);
			
			Member member2 = new Member();
			member2.setPoint(100000);
			
			Member member3 = new Member();
			member3.setPoint(100000);
			
			Member member4 = new Member();
			member4.setPoint(100000);
			
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			
		}
		
		public void menuInit() {
			
			Menu menu1 = new Menu();
			menu1.setName("아이스 아메리카노");
			menu1.setPrice(3000);
			
			Menu menu2 = new Menu();
			menu2.setName("카푸치노");
			menu2.setPrice(5000);
			
			Menu menu3 = new Menu();
			menu3.setName("밀크티");
			menu3.setPrice(5000);

			Menu menu4 = new Menu();
			menu4.setName("자몽에이드");
			menu4.setPrice(5500);
			
			Menu menu5 = new Menu();
			menu5.setName("카라멜 마끼야또");
			menu5.setPrice(6000);
			
			Menu menu6 = new Menu();
			menu6.setName("자바칩 프라푸치노");
			menu6.setPrice(7000);
			
			em.persist(menu1);
			em.persist(menu2);
			em.persist(menu3);
			em.persist(menu4);
			em.persist(menu5);
			em.persist(menu6);
		}
	
		public void orderInit() {
			orderService.order((long) 1, (long) 5, 3);
			orderService.order((long) 1, (long) 9, 1);
			orderService.order((long) 3, (long) 5, 1);
			orderService.order((long) 4, (long) 7, 3);
			orderService.order((long) 2, (long) 8, 5);
		}
	}

}
