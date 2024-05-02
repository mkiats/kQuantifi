package com.mkiats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentStrategyManager;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.temp.TempClass;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

	private final InvestmentStrategyManager investmentStrategyManager;
	private final FinancialRatioStrategyManager financialRatioStrategyManager;
	private final RetrievalService retrievalService;

	public void doExecute() throws JsonProcessingException {
		TempClass json = new TempClass();
		String jsonStr = json.getJsonStringShort();

		//        COMMENT OUT COZ API REQUEST GOT LIMIT
		//		String tickerData = retrievalService.fetchTickerData("TIME_SERIES_MONTHLY_ADJUSTED", "SPY");
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(jsonStr);

		InvestmentStrategy newService = investmentStrategyManager.getService(
			"DollarCostAverageStrategy"
		);
		HashMap<String, Object> hashmapParameter = new HashMap<>();
		hashmapParameter.put("timeSeriesStockData", timeSeriesStockData);
		hashmapParameter.put("periodicAmount", 100);
		System.out.println(hashmapParameter);
		InvestmentOutput testOutput = newService.executeStrategy(
			hashmapParameter
		);
		testOutput.getStockValue().forEach(element -> {
			System.out.println(element);
		});
		FinancialRatioStrategy newFinancialRatio = financialRatioStrategyManager.getService("CompoundAnnualGrowthRate");
		double cagr = newFinancialRatio.computeRatio(testOutput);
		System.out.println(cagr);
	}
}
