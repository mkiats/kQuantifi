package com.mkiats.services.registryServices.strategyServices;

import org.springframework.stereotype.Service;

@Service
public class DollarCostAverage implements StrategyService {

	// Calculate initial value and final value of the strategy
	/*
    Pseudo Code:

    Initialise currentStockQuantity & currentStockValue

    Iteration:
        - calculate addedStockQuantity by finding (DCA amount / current stock price)
        - Update currentStockQuantity
        - Add DCA amount to currentStockValue
        - Update currentStockValue

    End: Calculate averageEntryPrice
     */
	@Override
	public float executeStrategy() {
		return 0;
	}
}
