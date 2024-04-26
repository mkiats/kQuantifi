package com.mkiats.services.serviceStrategyFactory;

import com.mkiats.services.serviceStrategyFactory.metricServices.MetricStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricStrategyFactory {

	private final Map<String, MetricStrategy> metricServices = new HashMap<>();

	@Autowired
	public MetricStrategyFactory(
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
