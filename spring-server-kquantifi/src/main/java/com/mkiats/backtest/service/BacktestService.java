package com.mkiats.backtest.service;

import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.dto.service.BacktestResponse;
import com.mkiats.backtest.service.strategy.financialRatio.FinancialRatioStrategyManager;
import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentStrategyManager;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
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

		retrievalService.doExecute(theBacktestRequest);

		InvestmentStrategy desiredStrategy =
			investmentStrategyManager.getService(
				theBacktestRequest
					.getPortfolio()
					.getPortfolioSettings()
					.getInvestmentStrategy()
			);

		InvestmentOutput desiredStrategyResults =
			desiredStrategy.executeStrategy(theBacktestRequest);

		FinancialRatioOutput desiredStrategyRatios = new FinancialRatioOutput();
		List<String> financialRatioList =
			financialRatioStrategyManager.getAllServices();
		for (String financialRatioString : financialRatioList) {
			FinancialRatioStrategy financialRatio =
				financialRatioStrategyManager.getService(financialRatioString);
			desiredStrategyRatios = financialRatio.computeRatio(
				theBacktestRequest,
				desiredStrategyResults,
				desiredStrategyRatios
			);
		}

		BacktestResponse theBacktestResponse = new BacktestResponse();
		theBacktestResponse.setFinancialRatioOutput(desiredStrategyRatios);
		theBacktestResponse.setInvestmentOutput(desiredStrategyResults);

		//		try {
		//			PrettyJson.prettyPrintJson(theBacktestResponse);
		//			System.out.println("Json pretty print success...");
		//		} catch (Exception e) {
		//			throw new RuntimeException("Json pretty print failed...");
		//		}

		return theBacktestResponse;
	}
}
