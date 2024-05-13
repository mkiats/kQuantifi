package com.mkiats.backtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.dto.BacktestResponse;
import com.mkiats.backtest.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.backtest.service.strategy.investment.InvestmentStrategyManager;
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
		System.out.println("\n\nBacktest Service starting...\n\n");
		/*
		 * Retrieve necessary parameters from request
		 * Retrieve & convert TimeSeriesStockData from RetrievalService
		 * Load data into a hashmap
		 * Use InvestmentStrategyMgr to getService and execute service
		 * Save output to BacktestResponse object
		 * Use FinancialRatioStrategyMgr to getAllService and execute service 1 by 1 via loop
		 * Save output to BacktestResponse object		*
		 * */

		String financialDataString = retrievalService.fetchTickerData(theBacktestRequest.getTickerName(), theBacktestRequest.getFrequency());
		TimeSeriesStockData timeSeriesStockData = retrievalService.convertStringToTimeSeriesStockData(financialDataString);

		BacktestResponse backtestResponse = new BacktestResponse();

		return backtestResponse;
    }
}
