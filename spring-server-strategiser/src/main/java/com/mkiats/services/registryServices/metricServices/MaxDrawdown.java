package com.mkiats.services.registryServices.metricServices;

import org.springframework.stereotype.Service;

@Service
public class MaxDrawdown implements MetricService {

	// Inputs: {List<timeSeriesStockPrice>}
	@Override
	public float calculate() {
		return 0;
	}
}
