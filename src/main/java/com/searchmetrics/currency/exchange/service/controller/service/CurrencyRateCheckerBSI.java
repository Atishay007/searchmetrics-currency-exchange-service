package com.searchmetrics.currency.exchange.service.controller.service;

import org.springframework.stereotype.Service;

@Service
public interface CurrencyRateCheckerBSI {

	/**
	 * Getting latest rate response by hitting 3rd Party API. The response is in
	 * application/javascript, to map the response in Java Object, we have converted
	 * it into JSON format.
	 * 
	 * @return Rate Response.
	 */
	public String getLatestRateResponse();

	/**
	 * Getting historical rates of (1 Bitcoin to USD) with start and end date.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return historical rates
	 */
	public Object getHistoricalRatesUsingDateRange(String startDate, String endDate);
}
