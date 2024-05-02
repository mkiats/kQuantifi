package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.MetricStrategy;
import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements MetricStrategy {

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public float calculate() {
		return 0;
	}
}
