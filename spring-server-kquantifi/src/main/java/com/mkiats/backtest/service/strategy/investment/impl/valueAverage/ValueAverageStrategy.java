package com.mkiats.backtest.service.strategy.investment.impl.valueAverage;

import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;

import java.util.HashMap;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
@Setter
public class ValueAverageStrategy implements InvestmentStrategy {

	private ValueAverageParameter theParameter = new ValueAverageParameter();

	// func execute():
	// Input: {AlgorithmStrategyContext}
	// Output: {MetricContext}

	@Override
	public InvestmentOutput executeStrategy(
		HashMap<String, Object> hashmapParameter
	) {
		this.theParameter.deserialise(hashmapParameter);
		TimeSeriesStockData context =
			this.theParameter.getTimeSeriesStockData();
		System.out.println(
			"ValueAverage.executeStrategy() executed monthly with " +
			this.theParameter.getPeriodicAmount()
		);
		InvestmentOutput res = new InvestmentOutput();
		double cashNeeded = 0;
		double currentBal = 0;
		double currentQty = 0;
		double targetBal = 0;
		double targetRate = ((this.theParameter.getTargetRate() / 100) + 1);
		double previousClose = 0;
		double periodicAmount = 100;

		SequencedSet<String> keyList = context
			.getPriceList()
			.sequencedKeySet()
			.reversed();

		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = context
				.getPriceList()
				.get(dateKey);
			if (previousClose == 0) {
				previousClose = Double.parseDouble(
					timeSeriesStockPrice.getAdjustedClose()
				);
				currentQty = periodicAmount / previousClose;
				currentBal = periodicAmount;
				targetBal = periodicAmount;
				res
					.addTimestamp(dateKey)
					.addValue(currentBal)
					.addQuantity(currentQty);
			} else {
				double currentClose = Double.parseDouble(
					timeSeriesStockPrice.getAdjustedClose()
				);
				targetBal = targetBal + periodicAmount;
				double newCurrentBal = currentQty * currentClose;
				double balToBeAdded = targetBal - newCurrentBal;

				if (balToBeAdded > 0) {
					// Need to add
					double qtyToBeAdded = balToBeAdded / currentClose;
					currentQty = currentQty + qtyToBeAdded;
					currentBal = targetBal;
					res
						.addTimestamp(dateKey)
						.addValue(currentBal)
						.addQuantity(currentQty);
				} else {
					res
						.addTimestamp(dateKey)
						.addValue(currentBal)
						.addQuantity(currentQty);
				}
			}
		}
		return res;
	}
}
