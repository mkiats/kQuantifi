package com.mkiats.backtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkiats.backtest.dto.*;
import com.mkiats.commons.dataAccessObjects.TickerDaoImpl;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;
import com.mkiats.commons.exceptions.CustomDataProcessingException;
import com.mkiats.commons.repository.TickerRepository;
import com.mkiats.commons.temp.TempClass;
import com.mkiats.commons.utils.DateUtils;
import com.mkiats.commons.utils.PrettyJson;
import java.util.ArrayList;
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

	// Constructor injection via @RequiredArgsConstructor
	private final RestTemplate restTemplate;

	private final TickerDaoImpl tickerDaoImpl;

	public void doExecute(BacktestRequest backtestRequest) {
		ArrayList<String> tickerList = backtestRequest
			.getPortfolio()
			.getPortfolioTickers()
			.getTickerList();
		String timeframe = backtestRequest
			.getPortfolio()
			.getPortfolioSettings()
			.getFrequency();

		PortfolioQuery portfolioQuery = new PortfolioQuery();

		String earliestInceptionDate = "2024-06-30";
		for (String tickerName : tickerList) {
			String queryJson = this.fetchTickerData(timeframe, tickerName);
			TimeSeriesStockData queryObj =
				this.convertStringToTimeSeriesStockData(queryJson);

			boolean firstEntry = true;
			boolean existInDb = false;

			// Get the earliest common date among the tickers
			for (String timestamp : queryObj
				.getPriceList()
				.reversed()
				.keySet()) {
				if (firstEntry) {
					Ticker theTicker = new Ticker(tickerName, timestamp);
					existInDb = tickerDaoImpl.addTicker(theTicker);
					earliestInceptionDate = DateUtils.compareEarliestDate(
						earliestInceptionDate,
						"yyyy-MM-dd",
						timestamp,
						"yyyy-MM-dd"
					);
					firstEntry = false;
				}
				if (existInDb) {
					break;
				}

				// Add price data for each timestamp
				TimeSeriesStockPrice curPrice = queryObj
					.getPriceList()
					.get(timestamp);
				TickerPrice theTickerPrice = new TickerPrice(
					tickerName,
					timeframe,
					timestamp,
					curPrice.getOpen(),
					curPrice.getHigh(),
					curPrice.getLow(),
					curPrice.getClose(),
					curPrice.getAdjustedClose(),
					curPrice.getVolume(),
					curPrice.getDividendAmount()
				);
				tickerDaoImpl.addTickerPrice(theTickerPrice);
			}

			try {
				PrettyJson.prettyPrintJson(queryObj);
				System.out.println("Json pretty print success...");
			} catch (Exception e) {
				throw new RuntimeException("Json pretty print failed...");
			}
		}
	}

	private String getApiUrl(String timeframe, String theSymbol) {
		String requestTimeframe;
		String requestTickerSymbol;

		// Handles API requests
		if (timeframe != null) {
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

	private String fetchTickerData(String timeframe, String theSymbol) {
		//		String finalisedApiUrl = getApiUrl(timeframe, theSymbol);
		//		ResponseEntity<String> responseEntity = restTemplate.getForEntity(
		//			finalisedApiUrl,
		//			String.class
		//		);
		//		if (responseEntity.getStatusCode().is2xxSuccessful()) {
		//			return responseEntity.getBody();
		//		} else {
		//			return "Error: " + responseEntity.getStatusCode();
		//		}
		TempClass temp = new TempClass();
		return temp.getJsonStringShort();
	}

	public TimeSeriesStockData convertStringToTimeSeriesStockData(
		String jsonString
	) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(
				jsonString,
				TimeSeriesStockData.class
			);
		} catch (JsonProcessingException e) {
			throw new CustomDataProcessingException(
				"Json Processing exception"
			);
		}
	}
	/*
	 * Create a method for getting ticker data from cache and database
	 * Check if data exist in the cache, if yes then return the data in cache
	 * If data is stale in the cache, fetch the data and store inside the cache and database, return the data
	 * */
}
