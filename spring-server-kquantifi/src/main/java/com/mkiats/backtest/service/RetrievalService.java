package com.mkiats.backtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkiats.backtest.dto.Asset;
import com.mkiats.backtest.dto.Portfolio;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.exceptions.CustomDataProcessingException;

import java.util.ArrayList;
import java.util.Objects;

import com.mkiats.commons.utils.DateUtils;
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

	public String getEarliestCommonInceptionDate(ArrayList<Portfolio> thePortfolios) {
		long earliestCommonInceptionDateInUnix = -1;
		for (Portfolio portfolio : thePortfolios) {
			for (Asset asset: portfolio.getAssets()) {
				if (getAssetDataFromCache(asset.getAssetName())) {
					earliestCommonInceptionDateInUnix = 0;
				}
				else {
					String jsonStockData = this.fetchTickerData("WEEKLY", asset.getAssetName());
					TimeSeriesStockData timeSeriesStockData = this.convertStringToTimeSeriesStockData(jsonStockData);
					// Store data into cache
					String assetInceptionDate = timeSeriesStockData.getPriceList().reversed().firstEntry().getKey();
					long assetInceptionDateInUnix = DateUtils.convertStringToUnix(assetInceptionDate, "yyyy-MM-dd");
					earliestCommonInceptionDateInUnix = Math.min(earliestCommonInceptionDateInUnix, assetInceptionDateInUnix);
				}
			}
		}
		return DateUtils.convertUnixToString(earliestCommonInceptionDateInUnix, "yyyy-MM-dd");
	}

	private boolean getAssetDataFromCache(String theAssetName) {
		// If AssetData exist in cache, return AssetData;
		// Else return null;
		return false;
	}

	private boolean getAssetDataFromDB(String theAssetName) {
		// If AssetData exist in cache, return AssetData;
		// Else return null;
		return false;
	}

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

	private String fetchTickerData(String theTimeframe, String theSymbol) {
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


}
