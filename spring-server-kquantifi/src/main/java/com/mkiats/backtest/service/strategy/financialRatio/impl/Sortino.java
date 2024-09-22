package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.dto.service.TimeValue;
import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.List;
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
		BacktestRequest backtestRequest,
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing Sortino...");

		List<TimeValue> cagrList = financialRatioOutput
			.getCagr()
			.getChartData();
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (TimeValue cagrTimeValue : cagrList) {
			if (cagrTimeValue.value() < this.riskFreeRate) {
				stats.addValue(cagrTimeValue.value());
			}
		}

		double downsideDeviation = stats.getStandardDeviation();
		financialRatioOutput.setSortinoRatio(
			(financialRatioOutput.getCagr().getFinalCagr() -
				this.riskFreeRate) /
			downsideDeviation
		);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
