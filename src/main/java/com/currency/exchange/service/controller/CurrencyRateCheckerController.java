package com.currency.exchange.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currency.exchange.service.controller.service.CurrencyRateCheckerBS;

@RestController
public class CurrencyRateCheckerController {

	@Autowired
	private CurrencyRateCheckerBS currencyRateBS;

	@GetMapping("/getRate/bitcoin/to/usd")
	public String getLatestRateResponse() {
		return currencyRateBS.getLatestRateResponse();
	}

	@GetMapping("/getHistoricalRates/bitcoin/to/usd")
	public Object getHistoricalRatesUsingDateRange(@RequestParam("start") String startDate,
			@RequestParam("end") String endDate) {
		return currencyRateBS.getHistoricalRatesUsingDateRange(startDate, endDate);
	}
}
