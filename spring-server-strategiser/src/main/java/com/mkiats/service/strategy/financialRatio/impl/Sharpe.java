package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
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
