package com.mkiats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.MetricServiceRegistry;
import com.mkiats.services.registryServices.StrategyServiceRegistry;
import com.mkiats.services.registryServices.strategyServices.DollarCostAverage;
import com.mkiats.services.registryServices.strategyServices.StrategyService;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.DollarCostAverageParameter;
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
		String jsonStr = json.getJsonStringShort();

		//        COMMENT OUT COZ API REQUEST GOT LIMIT
		//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);

		DollarCostAverage theService = (DollarCostAverage) strategyServiceRegistry.getService("DollarCostAverage");
		DollarCostAverageParameter theParameter = theService.getDollarCostAverageParameter();
		theParameter.setTimeSeriesStockData(timeSeriesStockData);
		double avgEntryPrice = theService.executeStrategy();
		System.out.println("AvgEntryPrice is " + avgEntryPrice);

		try {
			Class<?> className = Class.forName("com.mkiats.services.registryServices.strategyServices.DollarCostAverage");
			Object castedService = className.cast(theService);
			System.out.println(castedService.getClass());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
}