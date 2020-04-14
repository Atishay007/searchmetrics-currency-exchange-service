package com.currency.exchange.service.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USD {
	private String code;
	private String rate;
	private String description;
	@JsonProperty(value = "rate_float")
	private double rateFloat;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(double rateFloat) {
		this.rateFloat = rateFloat;
	}
}
