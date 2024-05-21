package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;

import com.mkiats.commons.dataTransferObjects.TimeValue;
import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements FinancialRatioStrategy {

	private final List<String> ratioDependencies = new ArrayList<>();

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public FinancialRatioOutput computeRatio(
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing CAGR...");

		double bestCagr = Double.MIN_VALUE;
		double worstCagr = Double.MAX_VALUE;
		double numberOfPeriodsWithinYear = (investmentOutput
					.getTimeframe()
					.equalsIgnoreCase("monthly"))
			? 12.0
			: 52.1785;

		for (int i = 1; i <= investmentOutput.getChartSize(); i++) {
			TimeValue curTimeValue = investmentOutput.getChartData().get(i-1);
			double year = i / numberOfPeriodsWithinYear;
			double futureValue = curTimeValue.value();
			double cumulativePeriodicValue =
				i * investmentOutput.getPeriodicAmount();
			double cagr =
				((Math.pow(futureValue / cumulativePeriodicValue, (1 / year)) -
						1) *
					100);
			bestCagr = Math.max(bestCagr, cagr);
			worstCagr = Math.min(worstCagr, cagr);
			financialRatioOutput
				.getCagrOutput()
				.addCagrValue(cagr)
				.addTimestamp(curTimeValue.time());
			financialRatioOutput.getCagrOutput().addTimeValue(curTimeValue.time(), cagr);
		}
		financialRatioOutput.getCagrOutput().setBestCagr(bestCagr);
		financialRatioOutput.getCagrOutput().setWorstCagr(worstCagr);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
