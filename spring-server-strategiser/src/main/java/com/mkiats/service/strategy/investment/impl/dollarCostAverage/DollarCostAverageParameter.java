package com.mkiats.service.strategy.investment.impl.dollarCostAverage;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.service.strategy.investment.interfaces.InvestmentParameter;
import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
