package com.mkiats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.MetricServiceRegistry;
import com.mkiats.services.registryServices.StrategyServiceRegistry;
import com.mkiats.services.registryServices.strategyServices.DollarCostAverageParameter;
import com.mkiats.services.registryServices.strategyServices.StrategyService;
import com.mkiats.temp.TempClass;
import java.util.Arrays;
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
		String jsonStr = json.getJsonStringShort();

		//        COMMENT OUT COZ API REQUEST GOT LIMIT
		//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);
		DollarCostAverageParameter theParameter = new DollarCostAverageParameter();
		theParameter.setTimeSeriesStockData(timeSeriesStockData);

		StrategyService<?> currentService = strategyServiceRegistry.getService(
			"DollarCostAverage"
		);
		double averageEntryPrice = currentService.executeStrategy(
			theParameter
		);
		System.out.println(String.valueOf(averageEntryPrice));
	}
}
