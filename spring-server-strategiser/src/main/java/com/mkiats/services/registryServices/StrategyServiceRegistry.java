package com.mkiats.services.registryServices;

import com.mkiats.services.registryServices.strategyServices.StrategyService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.StrategyServiceParameter;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyServiceRegistry {

	private final Map<String, StrategyService> strategyServices =
		new HashMap<>();

	@Autowired
	public StrategyServiceRegistry(List<StrategyService> strategyServiceList) {
		strategyServiceList.forEach(
			service ->
				strategyServices.put(
					service.getClass().getSimpleName(),
				    service
				)
		);
	}

	public StrategyService getService(String serviceName) {
        return strategyServices.get(serviceName);
	}

	public void registerService(String serviceName, StrategyService strategyService) {
		strategyServices.put(serviceName, strategyService);
	}

}
