package com.mkiats.service.strategy.investment;

import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvestmentStrategyManager {

	private final Map<String, InvestmentStrategy> investmentStrategyServices =
		new HashMap<>();

	@Autowired
	public InvestmentStrategyManager(
		List<InvestmentStrategy> strategyServiceList
	) {
		strategyServiceList.forEach(service -> {
			investmentStrategyServices.put(service.getClass().getSimpleName(), service);
		});
	}

	public InvestmentStrategy getService(String serviceName) {
		return investmentStrategyServices.get(serviceName);
	}

	public void registerService(
		String serviceName,
		InvestmentStrategy algoService
	) {
		investmentStrategyServices.put(serviceName, algoService);
	}
}
