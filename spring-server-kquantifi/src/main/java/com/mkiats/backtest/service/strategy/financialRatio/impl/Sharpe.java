package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Sharpe implements FinancialRatioStrategy {

	private final double riskFreeRate = 3.00;
	private final List<String> ratioDependencies = List.of(
		"CompoundAnnualGrowthRate",
		"StandardDeviation"
	);

	@Override
	public FinancialRatioOutput computeRatio(
		BacktestRequest backtestRequest,
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing Sharpe...");

		double expectedRate = financialRatioOutput.getCagr().getFinalCagr();
		double standardDeviation = financialRatioOutput.getStandardDeviation();
		double sharpe = (expectedRate - this.riskFreeRate) / standardDeviation;
		financialRatioOutput.setSharpeRatio(sharpe);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
