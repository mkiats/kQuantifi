package com.mkiats.backtest.service.strategy.investment.impl.dollarCostAverage;

import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentParameter;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DollarCostAverageParameter implements InvestmentParameter {

	private TimeSeriesStockData timeSeriesStockData;
	private double periodicAmount;

	@Override
	public void deserialise(HashMap<String, Object> theHashmap) {
		if (theHashmap.containsKey("timeSeriesStockData")) {
			this.timeSeriesStockData = (TimeSeriesStockData) theHashmap.get(
				"timeSeriesStockData"
			);
		}
		if (theHashmap.containsKey("periodicAmount")) {
			this.periodicAmount = (double) ((int) theHashmap.get(
					"periodicAmount"
				));
		}
	}
}
