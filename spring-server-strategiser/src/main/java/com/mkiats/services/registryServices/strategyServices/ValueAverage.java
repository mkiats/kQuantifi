package com.mkiats.services.registryServices.strategyServices;

import org.springframework.stereotype.Service;

@Service
public class ValueAverage implements StrategyService {

	// Input: {List<TimeSeriesStockPrice>, ValueAverageRate}
	/*
    Pseudo code:

    Initialise cashBalance & investmentBalance & totalCashNeeded & benchmarkBalance

    Iteration:
        - Calculate the difference between investmentBalance and benchmarkBalance
        - Excess balance goes to cashBalance, Deficit balance takes money from cashBalance
     */
	@Override
	public float executeStrategy() {
		return 0;
	}
}
