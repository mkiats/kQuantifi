package com.mkiats.services.serviceStrategyFactory.algorithmServices;

import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyContext;

public interface AlgorithmStrategy {
	double executeStrategy(AlgorithmStrategyContext context);
}
