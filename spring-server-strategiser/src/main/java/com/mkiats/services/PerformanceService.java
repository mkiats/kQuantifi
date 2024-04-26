package com.mkiats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyContext;
import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyFactory;
import com.mkiats.services.serviceStrategyFactory.MetricStrategyFactory;
import com.mkiats.services.serviceStrategyFactory.algorithmServices.AlgorithmStrategy;
import com.mkiats.temp.TempClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

	private final AlgorithmStrategyFactory algorithmStrategyFactory;
	private final AlgorithmStrategyContext algorithmStrategyContext;
	private final MetricStrategyFactory metricStrategyFactory;
	private final RetrievalService retrievalService;

	public void doExecute() throws JsonProcessingException {
		TempClass json = new TempClass();
		String jsonStr = json.getJsonStringShort();

		//        COMMENT OUT COZ API REQUEST GOT LIMIT
		//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);

		AlgorithmStrategy newService = algorithmStrategyFactory.getService(
			"ValueAverage"
		);
		algorithmStrategyContext.setTimeSeriesStockData(timeSeriesStockData);
		algorithmStrategyContext.setValueAvg_benchmarkRate(5);
		algorithmStrategyContext.setValueAvg_benchmark("SPY");
		double dummyDouble = newService.executeStrategy(
			algorithmStrategyContext
		);
		System.out.println("AvgEntryPrice is " + dummyDouble);
	}
}
