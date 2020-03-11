package com.searchmetrics.currency.exchange.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SearchmetricsCurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchmetricsCurrencyExchangeServiceApplication.class, args);
	}
}
