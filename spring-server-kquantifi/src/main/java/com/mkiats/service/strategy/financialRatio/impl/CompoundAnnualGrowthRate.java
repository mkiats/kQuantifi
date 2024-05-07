package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements FinancialRatioStrategy {

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		double principal = investmentOutput.getInvestedAmount();
		double finalValue = investmentOutput.getStockValue().getLast();
		double numOfCompoundingPeriods = investmentOutput
			.getStockValue()
			.size();
		// Hardcorded to assume 1 period is 1 month
		// TODO: Retrieve time period from InvestmentOutput
		double timePeriod = numOfCompoundingPeriods / 12;

		return (
			(Math.pow((finalValue / principal), (1 / numOfCompoundingPeriods)) -
				1) *
			100 *
			timePeriod
		);
	}
}
