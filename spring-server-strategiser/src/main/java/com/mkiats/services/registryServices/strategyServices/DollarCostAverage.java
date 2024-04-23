package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import org.springframework.stereotype.Service;

@Service
public class DollarCostAverage implements StrategyService {

	private TimeSeriesStockData timeSeriesStockData;

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

	public DollarCostAverage(TimeSeriesStockData theStockData) {
		this.timeSeriesStockData = theStockData;
	}
}
