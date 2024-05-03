package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

@Service
public class StandardDeviation implements FinancialRatioStrategy {

	@Override
	public double computeRatio(InvestmentOutput investmentOutput) {
		ArrayList<Double> priceList = investmentOutput.getStockValue();
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Double d : priceList) {
			stats.addValue(d);
		}
		return stats.getStandardDeviation();
	}
}
