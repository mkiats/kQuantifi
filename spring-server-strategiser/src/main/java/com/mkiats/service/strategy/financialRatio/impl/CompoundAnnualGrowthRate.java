package com.mkiats.service.strategy.financialRatio.impl;

import com.mkiats.service.strategy.financialRatio.interfaces.MetricStrategy;
import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements MetricStrategy {

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public float calculate() {
		return 0;
	}
}
