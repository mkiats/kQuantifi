package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.DollarCostAverageParameter;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.StrategyServiceParameter;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.ValueAverageParameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ValueAverage implements StrategyService {

	// Input: {List<TimeSeriesStockPrice>, ValueAverageRate}
	/*
    Pseudo code:

    Initialise cashBalance & investmentBalance & totalCashNeeded & benchmarkBalance

    Iteration:
        - Calculate the difference between investmentBalance and benchmarkBalance
        - Excess balance goes to cashBalance, Deficit balance takes money from cashBalance
     */

	private ValueAverageParameter valueAverageParameter;


	@Override
	public double executeStrategy() {
		return 0;
	}

}
