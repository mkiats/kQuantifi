package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;

public interface StrategyService<T> {
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
	double executeStrategy(T parameters);

	void doExecute();
}
