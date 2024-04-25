package com.mkiats.services.registryServices;

import com.mkiats.services.registryServices.strategyServices.StrategyService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyServiceRegistry {

	private final Map<String, Pair<StrategyService<?>, Object>> strategyServices =
		new HashMap<>();

	@Autowired
	public StrategyServiceRegistry(List<StrategyService<?>> strategyServiceList) {
		strategyServiceList.forEach(
			service ->
				strategyServices.put(
					service.getClass().getSimpleName(),
					new Pair<>(service, null)
				)
		);
	}

	public StrategyService<?> getService(String serviceName) {
		Pair<StrategyService<?>, Object> thePair = strategyServices.get(serviceName);
		return thePair.a;
	}

	public <T> void registerService(String serviceName, StrategyService<T> strategyService, T parameter) {
		strategyServices.put(serviceName, new Pair<>(strategyService, parameter));
	}
}
