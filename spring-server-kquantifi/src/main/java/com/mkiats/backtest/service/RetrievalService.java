package com.mkiats.backtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkiats.backtest.dto.api.TimeSeriesStockData;
import com.mkiats.backtest.dto.api.TimeSeriesStockPrice;
import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.dto.service.PortfolioQuery;
import com.mkiats.commons.dataAccessObjects.TickerDaoImpl;
import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;
import com.mkiats.commons.exceptions.CustomDataProcessingException;
import com.mkiats.commons.repository.TickerPriceRepository;
import com.mkiats.commons.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RetrievalService {

	private final TickerPriceRepository tickerPriceRepository;

	@Value("${api.url.alphavantage}")
	private String baseApiUrl;

	@Value("${api.key.alphavantage}")
	private String apiKey;

	// Constructor injection via @RequiredArgsConstructor
	private final RestTemplate restTemplate;

	private final TickerDaoImpl tickerDaoImpl;

	public void doExecute(BacktestRequest backtestRequest) {
		System.out.println("Retrieval Service starting...");
		ArrayList<String> tickerList = backtestRequest
			.getPortfolio()
			.getPortfolioTickers()
			.getTickerList();
		String timeframe = backtestRequest
			.getPortfolio()
			.getPortfolioSettings()
			.getFrequency();

		PortfolioQuery portfolioQuery = new PortfolioQuery();

		String earliestInceptionDate = backtestRequest
			.getPortfolio()
			.getPortfolioSettings()
			.getStartDate();

		for (String tickerName : tickerList) {
			boolean firstEntry = true;
			boolean existInDb = tickerDaoImpl.existTicker(tickerName);

			if (!existInDb) { // Query financial data from 3rd party API
				String queryJson =
					this.fetchTickerDataFromApi(timeframe, tickerName);
				TimeSeriesStockData queryObj =
					this.convertStringToTimeSeriesStockData(queryJson);

				// Get the earliest common date among the tickers
				for (String timestamp : queryObj
					.getPriceList()
					.reversed()
					.keySet()) {
					if (firstEntry) {
						Ticker theTicker = new Ticker(tickerName, timestamp);
						existInDb = tickerDaoImpl.addTicker(theTicker);
						earliestInceptionDate = DateUtils.getLatestDate(
							earliestInceptionDate,
							"yyyy-MM-dd",
							timestamp,
							"yyyy-MM-dd"
						);
						firstEntry = false;
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
			}
			// Query financial data from cache/db TODO: CHANGE TO IF EXIST IN CACHE,
			Ticker theTicker = tickerDaoImpl.getTicker(tickerName);
			earliestInceptionDate = DateUtils.getLatestDate(
				earliestInceptionDate,
				"yyyy-MM-dd",
				theTicker.getInceptionDate(),
				"yyyy-MM-dd"
			);
		}
		portfolioQuery.setEarliestCommonInceptionDate(earliestInceptionDate);
		backtestRequest.setPortfolioQuery(portfolioQuery);
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

	private String fetchTickerDataFromApi(String timeframe, String theSymbol) {
		String finalisedApiUrl = getApiUrl(timeframe, theSymbol);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(
			finalisedApiUrl,
			String.class
		);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			return "Error: " + responseEntity.getStatusCode();
		}
		//		TempClass temp = new TempClass();
		//		return temp.getJsonStringShort();
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

	public TickerPrice fetchTickerDataFromDb(
		String tickerName,
		String timeframe,
		String timestamp
	) {
		return tickerDaoImpl.getTickerPrice(tickerName, timeframe, timestamp);
	}

	public List<String> fetchTimestamps(String tickerName, String timeframe) {
		return tickerDaoImpl.getTimestamps(tickerName, timeframe);
	}
}
