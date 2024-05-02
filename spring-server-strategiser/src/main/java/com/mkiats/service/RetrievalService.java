package com.mkiats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RetrievalService {

	@Value("${api.url.alphavantage}")
	private String baseApiUrl;

	@Value("${api.key.alphavantage}")
	private String apiKey;

	// Dependency injected
	private final RestTemplate restTemplate;

	private String getApiUrl(String theTimeframe, String theSymbol) {
		String requestTimeframe;
		String requestTickerSymbol;

		// Handles API requests
		if (theTimeframe != null) {
			requestTimeframe = "TIME_SERIES_MONTHLY_ADJUSTED";
		} else {
			requestTimeframe = "TIME_SERIES_WEEKLY_ADJUSTED";
		}
		requestTickerSymbol = Objects.requireNonNullElse(theSymbol, "SPY");

		return String.format(
			"%sfunction=%s&symbol=%s&apikey=%s",
			baseApiUrl,
			requestTimeframe,
			requestTickerSymbol,
			apiKey
		);
	}

	public String fetchTickerData(String theTimeframe, String theSymbol) {
		String finalisedApiUrl = getApiUrl(theTimeframe, theSymbol);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(
			finalisedApiUrl,
			String.class
		);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return "Error: " + responseEntity.getStatusCode();
		}
	}

	public TimeSeriesStockData convertStringToTimeSeriesStockData(
		String jsonString
	) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonString, TimeSeriesStockData.class);
	}
}
