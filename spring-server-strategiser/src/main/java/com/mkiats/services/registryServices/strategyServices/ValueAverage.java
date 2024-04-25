package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import org.springframework.stereotype.Service;

@Service
public class ValueAverage implements StrategyService<ValueAverageParameter> {

	// Input: {List<TimeSeriesStockPrice>, ValueAverageRate}
	/*
    Pseudo code:

    Initialise cashBalance & investmentBalance & totalCashNeeded & benchmarkBalance

    Iteration:
        - Calculate the difference between investmentBalance and benchmarkBalance
        - Excess balance goes to cashBalance, Deficit balance takes money from cashBalance
     */
	public double executeStrategy(ValueAverageParameter parameter) {
		return 0;
	}

	@Override
	public void doExecute() {
		System.out.println("Trial");
	}
}
