package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

@Service
public class Sortino implements FinancialRatioStrategy {

	private final List<String> ratioDependencies = List.of(
		"CompoundAnnualGrowthRate"
	);

	private final double riskFreeRate = 3.00;

	@Override
	public FinancialRatioOutput computeRatio(
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing Sortino...");

		List<Double> cagrList = financialRatioOutput
			.getCagrOutput()
			.getCagrArr();
		double[] cagrArr = ArrayUtils.toPrimitive(
			cagrList.toArray(new Double[0])
		);
		double cagr = financialRatioOutput.getCagrOutput().getCagr();
		double downsideDeviation = calculateDownsideDeviation(
			cagrArr,
			this.riskFreeRate
		);
		financialRatioOutput.setSortinoRatio(
			(cagr - this.riskFreeRate) / downsideDeviation
		);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}

	public static double calculateDownsideDeviation(
		double[] cagrArr,
		double targetRate
	) {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (double cagrValue : cagrArr) {
			if (cagrValue < targetRate) {
				stats.addValue(cagrValue);
			}
		}
		return stats.getStandardDeviation();
	}
}
