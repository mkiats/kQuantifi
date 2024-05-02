package com.mkiats.service.strategy.investment.interfaces;

import com.mkiats.service.strategy.investment.InvestmentStrategyContext;
import com.mkiats.service.strategy.investment.InvestmentStrategyOutput;
import java.util.HashMap;
import java.util.List;

public interface InvestmentStrategy {
	List<InvestmentStrategyOutput> executeStrategy(
		HashMap<String, Object> theParameters
	);
}
