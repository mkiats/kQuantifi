package com.mkiats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.AlgorithmServiceRegistry;
import com.mkiats.services.registryServices.StrategyServiceRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

	private final StrategyServiceRegistry strategyServiceRegistry;
	private final AlgorithmServiceRegistry algorithmServiceRegistry;
	private final RetrievalService retrievalService;

	public void doExecute() throws JsonProcessingException {
		String json =
			"""
			    {
			    "Meta Data": {
			        "1. Information": "Monthly Adjusted Prices and Volumes",
			        "2. Symbol": "SPY",
			        "3. Last Refreshed": "2024-04-19",
			        "4. Time Zone": "US/Eastern"
			    },
			     "Monthly Adjusted Time Series": {
			         "2024-04-19": {
			             "1. open": "523.8300",
			                     "2. high": "524.3800",
			                     "3. low": "493.8600",
			                     "4. close": "495.1600",
			                     "5. adjusted close": "495.1600",
			                     "6. volume": "1140576228",
			                     "7. dividend amount": "0.0000"
			         },
			          "2024-03-28": {
			              "1. open": "508.9800",
			              "2. high": "524.6100",
			              "3. low": "504.9100",
			              "4. close": "523.0700",
			              "5. adjusted close": "523.0700",
			              "6. volume": "1463191348",
			              "7. dividend amount": "1.5949"
			          },
			         "2024-02-28": {
			                      "1. open": "508.9800",
			                      "2. high": "524.6100",
			                      "3. low": "504.9100",
			                      "4. close": "523.0700",
			                      "5. adjusted close": "523.0700",
			                      "6. volume": "1463191348",
			                      "7. dividend amount": "1.5949"
			                  }
			     }
			    }
			""";
//        COMMENT OUT COZ API REQUEST GOT LIMIT
//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(json);
		System.out.println(timeSeriesStockData.getPriceList().keySet());
		System.out.println(timeSeriesStockData.getMetaData().getInformation());
		System.out.println(timeSeriesStockData.getPriceList());
	}
}
