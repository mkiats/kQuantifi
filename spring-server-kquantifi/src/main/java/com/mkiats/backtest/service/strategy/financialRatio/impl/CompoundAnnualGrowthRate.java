package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.dto.TimeValue;
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
		BacktestRequest backtestRequest,
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing CAGR...");

		double bestCagr = Double.MIN_VALUE;
		double worstCagr = Double.MAX_VALUE;
		double numberOfPeriodsWithinYear = (backtestRequest
					.getPortfolio()
					.getPortfolioSettings()
					.getFrequency()
					.equalsIgnoreCase("monthly"))
			? 12.0
			: 52.1785;

		for (int i = 1; i <= investmentOutput.getChartSize(); i++) {
			TimeValue curTimeValue = investmentOutput.getChartData().get(i - 1);
			double year = i / numberOfPeriodsWithinYear;
			double curValue = curTimeValue.value();
			double cumulativePeriodicValue =
				i *
					backtestRequest
						.getPortfolio()
						.getPortfolioSettings()
						.getPeriodicCashflow() +
				backtestRequest
					.getPortfolio()
					.getPortfolioSettings()
					.getInitialBalance();
			double cagr =
				((Math.pow(curValue / cumulativePeriodicValue, (1 / year)) -
						1) *
					100);
			bestCagr = Math.max(bestCagr, cagr);
			worstCagr = Math.min(worstCagr, cagr);
			//			System.out.println("CURRENT INDEX IS " + i);
			//			System.out.printf(
			//				"%f, %f, %f, %f%n",
			//				year,
			//				curValue,
			//				cumulativePeriodicValue,
			//				cagr
			//			);
			System.out.println();
			financialRatioOutput
				.getCagr()
				.addTimeValue(curTimeValue.time(), cagr, 0.0);
		}
		financialRatioOutput.getCagr().setBestCagr(bestCagr);
		financialRatioOutput.getCagr().setWorstCagr(worstCagr);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
