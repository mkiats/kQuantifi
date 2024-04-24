package com.mkiats.services.registryServices.metricServices;

import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements MetricService {

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public float calculate() {
		return 0;
	}
}
