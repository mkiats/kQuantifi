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

		List<Double> stockValue = investmentOutput.getStockValue();
		List<String> stockTimestamp = investmentOutput.getStockTimestamp();
		int totalCompoundingPeriods = stockTimestamp.size();
		double maxLoss = -1;
		double peak = Double.MIN_VALUE;
		int tempWorstDrawdownStartDateIndex = -1;
		int worstDrawdownStartDateIndex = -1;
		int worstDrawdownEndDateIndex = -1;
		boolean drawdownStarted = false;

		for (int i=0; i<totalCompoundingPeriods; i++) {
			double curPrice = stockValue.get(i);
			String curTimestamp = stockTimestamp.get(i);
			if (curPrice > peak) {
				// Not in drawdown
				drawdownStarted = false;
				peak = curPrice;
				financialRatioOutput.getMaxDrawdown().addDrawdownValue(0.0).addTimestamp(curTimestamp);
			} else {
				// Currently in drawdown
				if (!drawdownStarted) {
					// Note down start of dropdown
					drawdownStarted = true;
					tempWorstDrawdownStartDateIndex = i;
				}
				if (peak-curPrice < maxLoss) {
					// Confirmed worst drawdown
					worstDrawdownStartDateIndex = tempWorstDrawdownStartDateIndex;
					worstDrawdownEndDateIndex = i;
					maxLoss = peak-curPrice;
				}
				maxLoss = Math.max(maxLoss, peak - curPrice);
				financialRatioOutput.getMaxDrawdown().addTimestamp(curTimestamp).addDrawdownValue(peak-curPrice);
			}
			financialRatioOutput.getMaxDrawdown().setStartDateOfWorstDrawdownIndex(worstDrawdownStartDateIndex);
			financialRatioOutput.getMaxDrawdown().setEndDateOfWorstDrawdownIndex(worstDrawdownEndDateIndex);
			financialRatioOutput.getMaxDrawdown().setWorstDrawdownValue(maxLoss);
		}

		financialRatioOutput.getMaxDrawdown().setWorstDrawdownValue(maxLoss);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
