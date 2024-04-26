package com.mkiats.services.serviceStrategyFactory;

import com.mkiats.services.serviceStrategyFactory.algorithmServices.AlgorithmStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlgorithmStrategyFactory {

	private final Map<String, AlgorithmStrategy> strategyServices =
		new HashMap<>();

	@Autowired
	public AlgorithmStrategyFactory(
		List<AlgorithmStrategy> strategyServiceList
	) {
		strategyServiceList.forEach(service -> {
			strategyServices.put(service.getClass().getSimpleName(), service);
		});
	}

	public AlgorithmStrategy getService(String serviceName) {
		return strategyServices.get(serviceName);
	}

	public void registerService(
		String serviceName,
		AlgorithmStrategy algoService
	) {
		strategyServices.put(serviceName, algoService);
	}
}
