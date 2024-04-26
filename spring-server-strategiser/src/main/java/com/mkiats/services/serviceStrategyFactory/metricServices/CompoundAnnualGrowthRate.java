package com.mkiats.services.serviceStrategyFactory.metricServices;

import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements MetricStrategy {

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public float calculate() {
		return 0;
	}
}
