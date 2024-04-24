package com.mkiats.services.registryServices;

import com.mkiats.services.registryServices.metricServices.MetricService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricServiceRegistry {

	private final Map<String, MetricService> metricServices =
		new HashMap<>();

	@Autowired
	public MetricServiceRegistry(
		List<MetricService> metricServicesList
	) {
		metricServicesList.forEach(
			service ->
					metricServices.put(
					service.getClass().getSimpleName(),
					service
				)
		);
	}

	public MetricService getMetricService(String serviceName) {
		return metricServices.get(serviceName);
	}
}
