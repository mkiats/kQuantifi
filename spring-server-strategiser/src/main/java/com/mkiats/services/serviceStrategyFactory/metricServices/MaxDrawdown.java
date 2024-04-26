package com.mkiats.services.serviceStrategyFactory.metricServices;

import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements MetricStrategy {

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public float calculate() {
		return 0;
	}
}
