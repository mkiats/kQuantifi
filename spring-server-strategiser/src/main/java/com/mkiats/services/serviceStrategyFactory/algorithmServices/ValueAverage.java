package com.mkiats.services.serviceStrategyFactory.algorithmServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.SequencedSet;

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
			context.getValueAvg_benchmarkRate()
		);

		double cashBal = 0;
		double investedBal = 0;
		double investQty = 0;
		double benchmarkBal = 0;
		double benchmarkRate = context.getValueAvg_benchmarkRate();
		double previousClose = 0;


		SequencedSet<String> keyList = context
				.getTimeSeriesStockData()
				.getPriceList()
				.sequencedKeySet()
				.reversed();

		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = context
					.getTimeSeriesStockData()
					.getPriceList()
					.get(dateKey);
			if (previousClose == 0) {
				double initialBal = context.getValueAvg_initialBalance();
				previousClose = Double.parseDouble(timeSeriesStockPrice.getAdjustedClose());
				investQty = investQty + (initialBal/previousClose);
				investedBal = initialBal;
				benchmarkBal = initialBal;
			} else {
				double curClose = Double.parseDouble(
						timeSeriesStockPrice.getAdjustedClose()
				);
				double percentageChange = (curClose-previousClose)*100/previousClose;
				benchmarkBal = benchmarkBal * ((benchmarkRate/100)+1);
				double rebalancedAmount = investedBal - benchmarkBal;
				cashBal = cashBal + rebalancedAmount;

			}


		}

		return 0;
	}

	public double calculatePercentageChange(double curClose, double prevClose) {
		
	}
}
