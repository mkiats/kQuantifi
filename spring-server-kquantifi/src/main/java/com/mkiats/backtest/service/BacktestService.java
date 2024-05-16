package com.mkiats.backtest.service;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.dto.BacktestResponse;
import com.mkiats.backtest.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentStrategyManager;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.temp.TempClass;
import com.mkiats.commons.utils.PrettyJson;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BacktestService {

	// Constructor injection via @RequiredArgsConstructor
	private final InvestmentStrategyManager investmentStrategyManager;
	// Constructor injection via @RequiredArgsConstructor
	private final FinancialRatioStrategyManager financialRatioStrategyManager;
	// Constructor injection via @RequiredArgsConstructor
	private final RetrievalService retrievalService;

	public BacktestResponse doExecute(BacktestRequest theBacktestRequest) {
		System.out.println("Backtest Service starting...\n\n");
		/*
		 * Retrieve necessary parameters from request
		 * Retrieve & convert TimeSeriesStockData from RetrievalService
		 * Load data into a hashmap
		 * Use InvestmentStrategyMgr to getService and execute service
		 * Save output to BacktestResponse object
		 * Use FinancialRatioStrategyMgr to getAllService and execute service 1 by 1 via loop
		 * Save output to BacktestResponse object		*
		 * */

		//		String financialDataString = retrievalService.fetchTickerData(theBacktestRequest.getTickerName(), theBacktestRequest.getFrequency());
		String financialDataString = new TempClass().getJsonStringShort();
		TimeSeriesStockData timeSeriesStockData =
			retrievalService.convertStringToTimeSeriesStockData(
				financialDataString
			);

		InvestmentStrategy desiredStrategy =
			investmentStrategyManager.getService(
				theBacktestRequest.getDesiredStrategy()
			);
		InvestmentOutput desiredStrategyResults =
			desiredStrategy.executeStrategy(
				theBacktestRequest,
				timeSeriesStockData
			);

		FinancialRatioOutput desiredStrategyRatios = new FinancialRatioOutput();
		List<String> financialRatioList =
			financialRatioStrategyManager.getAllServices();
		for (String financialRatioString : financialRatioList) {
			FinancialRatioStrategy financialRatio =
				financialRatioStrategyManager.getService(financialRatioString);
			desiredStrategyRatios = financialRatio.computeRatio(
				desiredStrategyResults,
				desiredStrategyRatios
			);
		}

		BacktestResponse backtestResponse = new BacktestResponse();
		backtestResponse.setFinancialRatioOutput(desiredStrategyRatios);
		backtestResponse.setInvestmentOutput(desiredStrategyResults);

		try {
			PrettyJson.prettyPrintJson(backtestResponse);
		} catch (Exception e) {
			throw new RuntimeException("Json pretty print failed...");
		}
		return backtestResponse;
	}
}
