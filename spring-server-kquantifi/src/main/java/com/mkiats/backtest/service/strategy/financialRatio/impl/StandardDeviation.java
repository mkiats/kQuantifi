package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

@Service
public class StandardDeviation implements FinancialRatioStrategy {

	private final List<String> ratioDependencies = List.of(
		"CompoundAnnualGrowthRate"
	);

	@Override
	public FinancialRatioOutput computeRatio(
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing Standard deviation...");

		List<Double> cagrList = financialRatioOutput
			.getCagrOutput()
			.getCagrArr();
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Double cagrValue : cagrList) {
			stats.addValue(cagrValue);
		}
		double stdDev = stats.getStandardDeviation();
		financialRatioOutput.setStandardDeviation(stdDev);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
