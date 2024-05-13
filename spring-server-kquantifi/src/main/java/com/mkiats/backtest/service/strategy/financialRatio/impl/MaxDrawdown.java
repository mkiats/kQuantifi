package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements FinancialRatioStrategy {

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		double maxLoss = -1;
		double peak = -1;
		List<Double> priceList = investmentOutput.getStockValue();

		for (int i = 0; i < priceList.size(); i++) {
			double curPrice = priceList.get(i);
			if (curPrice > peak) {
				peak = curPrice;
			} else {
				maxLoss = Math.max(maxLoss, peak - curPrice);
			}
		}
		return maxLoss;
	}
}
