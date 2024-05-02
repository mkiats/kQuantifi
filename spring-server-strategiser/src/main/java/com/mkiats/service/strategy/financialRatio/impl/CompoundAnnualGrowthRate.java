package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements FinancialRatioStrategy {

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		double initialValue = investmentOutput.getStockValue().getFirst();
		double finalValue = investmentOutput.getStockValue().getLast();
		double numOfPeriods = investmentOutput.getStockValue().size();

        return (Math.pow((finalValue/initialValue), (1/numOfPeriods)) - 1) * 100;
	}
}
