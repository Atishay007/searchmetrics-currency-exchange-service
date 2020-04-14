package com.currency.exchange.service.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateResponse {
	@JsonProperty
	private Bpi bpi;

	public Bpi getBpi() {
		return bpi;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}
}
