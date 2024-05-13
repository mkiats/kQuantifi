package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import org.springframework.stereotype.Service;

@Service
public class Sharpe implements FinancialRatioStrategy {

	private final CompoundAnnualGrowthRate compoundAnnualGrowthRate;
	private final StandardDeviation standardDeviation;
	private double riskFreeRate = 5.00;

	// Constructor injection
	public Sharpe(CompoundAnnualGrowthRate theRate, StandardDeviation theSd) {
		this.compoundAnnualGrowthRate = theRate;
		this.standardDeviation = theSd;
	}

	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		double expectedRate =
			this.compoundAnnualGrowthRate.computeRatio(investmentOutput);
		double standardDeviation =
			this.standardDeviation.computeRatio(investmentOutput);
		return (expectedRate - riskFreeRate) / standardDeviation;
	}
}
