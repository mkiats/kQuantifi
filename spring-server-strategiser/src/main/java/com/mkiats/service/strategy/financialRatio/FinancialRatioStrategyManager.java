package com.mkiats.service.strategy.financialRatio;

import com.mkiats.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
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

	@Autowired // Construction injection
	public FinancialRatioStrategyManager(
		List<FinancialRatioStrategy> strategyServiceList
	) {
		strategyServiceList.forEach(
			service ->
				financialRatioStrategyServices.put(
					service.getClass().getSimpleName(),
					service
				)
		);
	}

	public FinancialRatioStrategy getService(String serviceName) {
		return financialRatioStrategyServices.get(serviceName);
	}
}
