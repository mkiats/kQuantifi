package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.mkiats.commons.dataTransferObjects.TimeValue;
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
		double peak = Double.MIN_VALUE;
		String tempWorstDrawdownStartDateIndex = "";
		String worstDrawdownStartDateIndex= "";
		String worstDrawdownEndDateIndex= "";
		boolean drawdownStarted = false;

		for (TimeValue theTimeValue: investmentOutput.getChartData()) {
			String curTimestamp = theTimeValue.time();
			double curPrice = theTimeValue.value();

			if (curPrice > peak) {
				// Not in drawdown
				drawdownStarted = false;
				peak = curPrice;
				financialRatioOutput.getMaxDrawdown().addTimeValue(curTimestamp, 100.0);
			} else {
				// Currently in drawdown
				if (!drawdownStarted) {
					// Note down start of dropdown
					drawdownStarted = true;
					tempWorstDrawdownStartDateIndex = curTimestamp;
				}
				if (peak - curPrice > maxLoss) {
					// Confirmed worst drawdown
					worstDrawdownStartDateIndex =
						tempWorstDrawdownStartDateIndex;
					worstDrawdownEndDateIndex = curTimestamp;
					maxLoss = Math.max(maxLoss, peak - curPrice);
				}
				financialRatioOutput.getMaxDrawdown().addTimeValue(curTimestamp, calculateDrawdownValue(curPrice, peak));
			}
		}
		financialRatioOutput
			.getMaxDrawdown()
			.setStartDateOfWorstDrawdownIndex(worstDrawdownStartDateIndex);
		financialRatioOutput
			.getMaxDrawdown()
			.setEndDateOfWorstDrawdownIndex(worstDrawdownEndDateIndex);
		financialRatioOutput.getMaxDrawdown().setWorstDrawdownValue(maxLoss);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}

	public double calculateDrawdownValue(double curPrice, double peak) {
		BigDecimal rawDrawdown = new BigDecimal((peak-curPrice)*100/peak);
		BigDecimal roundedDrawdown = rawDrawdown.setScale(2, RoundingMode.HALF_UP);
		return roundedDrawdown.doubleValue();
	}
}
