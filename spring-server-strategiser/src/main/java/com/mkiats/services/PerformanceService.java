package com.mkiats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.MetricServiceRegistry;
import com.mkiats.services.registryServices.StrategyServiceRegistry;
import com.mkiats.temp.TempClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

	private final StrategyServiceRegistry strategyServiceRegistry;
	private final MetricServiceRegistry metricServiceRegistry;
	private final RetrievalService retrievalService;

	public void doExecute() throws JsonProcessingException {

		TempClass json = new TempClass();
		String jsonStr = json.getJsonString();


//        COMMENT OUT COZ API REQUEST GOT LIMIT
//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);
		System.out.println(timeSeriesStockData.getPriceList().keySet());
		System.out.println(timeSeriesStockData.getMetaData().getInformation());
		System.out.println(jsonStr);
	}
}
