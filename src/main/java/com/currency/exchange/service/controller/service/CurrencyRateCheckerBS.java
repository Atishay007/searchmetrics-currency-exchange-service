package com.currency.exchange.service.controller.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency.exchange.service.controller.response.RateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyRateCheckerBS implements CurrencyRateCheckerBSI {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRateCheckerBS.class);
	private static final String RESPONSE = "Exchange rate for Bitcoin to US-Dollar (1 Bitcoin = x USD) is: ";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final String GET_RATE_BITCOIN_TO_USD_URL = "https://api.coindesk.com/v1/bpi/currentprice/USD.json";
	private static final RestTemplate REST_TEMPLATE = createRestTemplateAndAddConverter();
	private RateResponse rateResponse = null;

	/**
	 * {@inheritDoc}
	 */
	public String getLatestRateResponse() {
		String finalReponse = null;
		if (rateResponse.getBpi() != null && rateResponse.getBpi().getUsd() != null) {
			finalReponse = RESPONSE + rateResponse.getBpi().getUsd().getRateFloat();
		}
		return finalReponse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getHistoricalRatesUsingDateRange(String startDate, String endDate) {
		RestTemplate restTemplate = createRestTemplateAndAddConverter();
		String historicalRatesResponse = restTemplate.getForObject(
				"https://api.coindesk.com/v1/bpi/historical/close.json?start=" + startDate + "&end=" + endDate,
				String.class);

		Map<String, Object> map = null;
		if (null != historicalRatesResponse) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				map = mapper.readValue(historicalRatesResponse, new TypeReference<Map<String, Object>>() {
				});
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (map != null) {
			return map.get("bpi");
		}
		return "Unable to find historical rates within specified date range, Please try Again";
	}

	/**
	 * Creating a new Rest Template and adding converter as response is in
	 * application/javascript and we have to convert it in application/json.
	 * 
	 * @return RestTemplate
	 */
	private static RestTemplate createRestTemplateAndAddConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		// Converting application/javascript response to application/json.
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL, MediaType.APPLICATION_JSON));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(converter);
		LOGGER.info("RestTemplate initialized successfully");
		return restTemplate;
	}

	/**
	 * This will constantly update the In-memory RateResponse according to the delay
	 * time configured in application.properties.
	 */
	@Scheduled(fixedDelayString = "${scheduler.fixed.delay}")
	private void getLatestResponse() {
		LOGGER.info("Fetched latest Rate for 1 USD to Bitcoin at{}", dateFormat.format(new Date()));
		rateResponse = REST_TEMPLATE.getForObject(GET_RATE_BITCOIN_TO_USD_URL, RateResponse.class);
	}

}
