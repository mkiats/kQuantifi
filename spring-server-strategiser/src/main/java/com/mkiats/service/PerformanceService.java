package com.mkiats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import com.mkiats.service.strategy.investment.InvestmentStrategyManager;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.temp.TempClass;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

	// Constructor injection via @RequiredArgsConstructor
	private final InvestmentStrategyManager investmentStrategyManager;
	// Constructor injection via @RequiredArgsConstructor
	private final FinancialRatioStrategyManager financialRatioStrategyManager;
	// Constructor injection via @RequiredArgsConstructor
	private final RetrievalService retrievalService;

	public void doExecute() throws JsonProcessingException {
		//		Retrieve & deserialize json api data
		//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TempClass json = new TempClass();
		String jsonStr = json.getJsonStringShort();
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);

		//		 Convert FormData -> HashMap
		HashMap<String, Object> hashmapParameter = new HashMap<>();
		hashmapParameter.put("timeSeriesStockData", timeSeriesStockData);
		hashmapParameter.put("periodicAmount", 100);
		System.out.println(hashmapParameter);

		//		 Retrieve & execute investment service
		InvestmentStrategy newService = investmentStrategyManager.getService(
			"DollarCostAverageStrategy"
		);
		InvestmentOutput testOutput = newService.executeStrategy(
			hashmapParameter
		);
		System.out.println(testOutput.getStockValue());

		// Retrieve & execute financial ratio service
		FinancialRatioStrategy newFinancialRatio =
			financialRatioStrategyManager.getService(
				"CompoundAnnualGrowthRate"
			);
		double cagr = newFinancialRatio.computeRatio(testOutput);
		System.out.println(cagr);
		double maxDrawdown = financialRatioStrategyManager
			.getService("MaxDrawdown")
			.computeRatio(testOutput);
		System.out.println(maxDrawdown);
	}
}
