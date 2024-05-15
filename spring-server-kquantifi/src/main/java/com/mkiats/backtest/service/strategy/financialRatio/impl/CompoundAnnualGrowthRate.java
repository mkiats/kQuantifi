package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;
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

		ArrayList<String> stockTimestamp = investmentOutput.getStockTimestamp();
		ArrayList<Double> stockValue = investmentOutput.getStockValue();
		ArrayList<Double> stockQuantity = investmentOutput.getStockQuantity();
		int totalCompoundingPeriods = stockTimestamp.size();
		double bestCagr = Double.MIN_VALUE;
		double worstCagr = Double.MAX_VALUE;

		for (int i=1; i<=totalCompoundingPeriods; i++) {
			double year = i/52.1775;
			double futureValue = stockValue.get(i-1) * stockQuantity.get(i-1);
			double cumulativePeriodicValue = i*investmentOutput.getPeriodicAmount();
			double cagr = ((Math.pow(futureValue / cumulativePeriodicValue , (1 / year)) - 1) * 100);
			bestCagr = Math.max(bestCagr, cagr);
			worstCagr = Math.max(worstCagr, cagr);
			financialRatioOutput.getCagrOutput().addCagrValue(cagr).addTimestamp(stockTimestamp.get(i-1));
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
