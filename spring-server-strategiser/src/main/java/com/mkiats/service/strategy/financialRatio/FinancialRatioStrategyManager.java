package com.mkiats.service.strategy.financialRatio;

import com.mkiats.service.strategy.financialRatio.interfaces.MetricStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialRatioStrategyManager {

	private final Map<String, MetricStrategy> metricServices = new HashMap<>();

	@Autowired
	public FinancialRatioStrategyManager(
		List<MetricStrategy> metricServicesListStrategy
	) {
		metricServicesListStrategy.forEach(
			service ->
				metricServices.put(service.getClass().getSimpleName(), service)
		);
	}

	public MetricStrategy getMetricService(String serviceName) {
		return metricServices.get(serviceName);
	}
}
