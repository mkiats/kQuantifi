package com.mkiats.service.strategy.investment.impl.valueAverage;

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
public class ValueAverageParameter implements InvestmentParameter {

	private TimeSeriesStockData timeSeriesStockData;
	private double periodicAmount;
	private double targetRate;

	@Override
	public void deserialise(HashMap<String, Object> theHashmap) {}
}
