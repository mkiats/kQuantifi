package com.mkiats.backtest.service.strategy.investment.impl.valueAverage;

import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentParameter;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValueAverageParameter implements InvestmentParameter {

	private TimeSeriesStockData timeSeriesStockData;
	private double periodicAmount;
	private double targetRate;

	@Override
	public void deserialise(HashMap<String, Object> theHashmap) {
		if (theHashmap.containsKey("timeSeriesStockData")) {
			this.timeSeriesStockData = (TimeSeriesStockData) theHashmap.get(
				"timeSeriesStockData"
			);
		}
		if (theHashmap.containsKey("periodicAmount")) {
			this.periodicAmount = (double) (int) theHashmap.get(
				"periodicAmount"
			);
		}
		if (theHashmap.containsKey("targetRate")) {
			this.targetRate = (double) (int) theHashmap.get("targetRate");
		}
	}
}
