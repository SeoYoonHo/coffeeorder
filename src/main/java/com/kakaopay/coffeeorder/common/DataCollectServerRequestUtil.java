package com.kakaopay.coffeeorder.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class DataCollectServerRequestUtil {
	private WebClient webClient;

	@PostConstruct
	public void initWebClient() {
		webClient = WebClient.create("https://ccd0f3fa-7e34-432e-9dc9-46e9ad45d828.mock.pstmn.io");
	}

	public void sendOrderData(Long member_id, Long menu_id, int price) {
//		log.info("call!!!!!!!!!!");
//		log.info("member_id!!!!!!!!!!" + member_id);
//		log.info("menu_id!!!!!!!!!!" + menu_id);
//		log.info("price!!!!!!!!!!" + price);

		Map<String, Integer> bodyMap = new HashMap<>();
		bodyMap.put("member_id", member_id.intValue());
		bodyMap.put("menu_id", menu_id.intValue());
		bodyMap.put("price", price);

		Mono<ResonseCollecDataSystem> mono =
				webClient.post()
						 .uri("/post")
						 .body(BodyInserters.fromValue(bodyMap))
						 .accept(MediaType.APPLICATION_JSON)
						 .retrieve()
						 .bodyToMono(ResonseCollecDataSystem.class)
						 .doOnSuccess(response -> log.info("result!!!!" + response.toString()))
						 .doOnError(e -> log.error("error!!!!!" +e.getMessage(), e));
		
		mono.subscribe(response -> log.info("please!!!!!!!!!!!!!!!" + response.toString()));

	}

	@Data
	@ToString
	static class ResonseCollecDataSystem {
		private String result;
	}

}