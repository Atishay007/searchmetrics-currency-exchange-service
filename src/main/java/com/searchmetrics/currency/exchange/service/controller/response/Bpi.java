package com.searchmetrics.currency.exchange.service.controller.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bpi {
	@JsonProperty(value = "USD")
	private USD usd;
	private Map<String, String> historicalRates;

	public USD getUsd() {
		return usd;
	}

	public void setUsd(USD usd) {
		this.usd = usd;
	}

	public Map<String, String> getHistoricalRates() {
		return historicalRates;
	}

	public void setHistoricalRates(Map<String, String> historicalRates) {
		this.historicalRates = historicalRates;
	}
}
