package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements FinancialRatioStrategy {

	private final List<String> ratioDependencies = new ArrayList<>();

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public FinancialRatioOutput computeRatio(
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing MaxDrawdown...");
		double maxLoss = -1;
		double peak = -1;
		List<Double> priceList = investmentOutput.getStockValue();

		for (double curPrice : priceList) {
			if (curPrice > peak) {
				peak = curPrice;
			} else {
				maxLoss = Math.max(maxLoss, peak - curPrice);
			}
		}

		financialRatioOutput.getMaxDrawdown().setWorstDrawdownValue(maxLoss);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
