package com.mkiats.backtest.service.strategy.financialRatio;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialRatioStrategyManager {

	private final Map<
		String,
            FinancialRatioStrategy
	> financialRatioStrategyServices = new HashMap<>();

	private final List<String> financialRatioStrategyServicesList =
		new ArrayList<>();

	@Autowired // Construction injection
	public FinancialRatioStrategyManager(
		List<FinancialRatioStrategy> strategyServiceList
	) {
		strategyServiceList.forEach(service -> {
			this.financialRatioStrategyServices.put(
					service.getClass().getSimpleName(),
					service
				);
			this.financialRatioStrategyServicesList.add(
					service.getClass().getSimpleName()
				);
		});
	}

	public FinancialRatioStrategy getService(String serviceName) {
		return financialRatioStrategyServices.get(serviceName);
	}

	public List<String> getAllServices() {
		return financialRatioStrategyServicesList;
	}
}
