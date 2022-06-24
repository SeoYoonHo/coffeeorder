package com.kakaopay.coffeeorder.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.coffeeorder.domain.Order;
import com.kakaopay.coffeeorder.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("api/v1/orderMenu")
	public CreateOrderResponse orderMenu(@RequestBody @Valid CreateOrderRequest request) {
		Order createOrder = orderService.order(request.member_id, request.menu_id, 1);
		return new CreateOrderResponse(createOrder.getMember().getId(), createOrder.getMember().getPoint(),
				"주문이 완료되었습니다.");
	}

	@Data
	static class CreateOrderRequest {
		private Long member_id;
		private Long menu_id;
	}

	@Data
	@AllArgsConstructor
	static class CreateOrderResponse {
		private Long member_id;
		private int point;
		private String msg;
	}

}
