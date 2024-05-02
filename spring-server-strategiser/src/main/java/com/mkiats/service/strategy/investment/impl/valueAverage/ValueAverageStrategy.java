package com.mkiats.service.strategy.investment.impl.valueAverage;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.service.strategy.investment.InvestmentStrategyContext;
import com.mkiats.service.strategy.investment.InvestmentStrategyOutput;
import com.mkiats.service.strategy.investment.impl.dollarCostAverage.DollarCostAverageParameter;
import com.mkiats.service.strategy.investment.interfaces.InvestmentParameter;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ValueAverageStrategy implements InvestmentStrategy {

	private ValueAverageParameter theParameter = new ValueAverageParameter();

	// func execute():
	// Input: {AlgorithmStrategyContext}
	// Output: {MetricContext}

	@Override
	public List<InvestmentStrategyOutput> executeStrategy(
		HashMap<String, Object> hashmapParameter
	) {
		this.theParameter.deserialise(hashmapParameter);
		TimeSeriesStockData context =
			this.theParameter.getTimeSeriesStockData();
		System.out.println(
			"ValueAverage.executeStrategy() executed monthly with " +
			this.theParameter.getPeriodicAmount()
		);
		InvestmentStrategyOutput res;
		List<InvestmentStrategyOutput> resList = new ArrayList<>();
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
				res = new InvestmentStrategyOutput(
					dateKey,
					currentBal,
					currentQty
				);
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
					res = new InvestmentStrategyOutput(
						dateKey,
						currentBal,
						currentQty
					);
				} else {
					res = new InvestmentStrategyOutput(
						dateKey,
						newCurrentBal,
						currentQty
					);
				}
				resList.add(res);
			}
		}
		return resList;
	}
}
