package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements FinancialRatioStrategy {

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		return 0;
	}
}
