package com.mkiats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.dataTransferObjects.backtest.BacktestRequest;
import com.mkiats.dataTransferObjects.backtest.BacktestResponse;
import com.mkiats.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import com.mkiats.service.strategy.investment.InvestmentStrategyManager;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.temp.TempClass;
import com.mkiats.utils.dateConversion;
import java.util.HashMap;
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

	public BacktestResponse doExecute(BacktestRequest theBacktestRequest)
		throws JsonProcessingException {
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
		BacktestResponse backtestResponse = new BacktestResponse();

		return backtestResponse;
	}
}
