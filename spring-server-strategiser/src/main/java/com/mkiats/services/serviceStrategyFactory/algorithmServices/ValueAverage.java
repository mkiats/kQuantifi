package com.mkiats.services.serviceStrategyFactory.algorithmServices;

import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ValueAverage implements AlgorithmStrategy {

	// Input: {List<TimeSeriesStockPrice>, ValueAverageRate}
	/*
    Pseudo code:

    Initialise cashBalance & investmentBalance & totalCashNeeded & benchmarkBalance

    Iteration:
        - Calculate the difference between investmentBalance and benchmarkBalance
        - Excess balance goes to cashBalance, Deficit balance takes money from cashBalance
     */
	@Override
	public double executeStrategy(AlgorithmStrategyContext context) {
		System.out.println(
			"ValueAverage.executeStrategy() called, using " +
			context.getValueAvg_benchmark() +
			" and " +
			context.getValueAvg_benchmarkRate()
		);
		return 0;
	}
}
