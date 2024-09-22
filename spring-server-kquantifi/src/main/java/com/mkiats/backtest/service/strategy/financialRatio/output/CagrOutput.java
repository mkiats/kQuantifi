package com.mkiats.backtest.service.strategy.financialRatio.output;

import com.mkiats.backtest.dto.service.TimeValue;
import com.mkiats.backtest.exceptions.RatioComputationException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CagrOutput {

	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private Double bestCagr;
	private Double worstCagr;

	public double getFinalCagr() {
		if (!chartData.isEmpty()) {
			return this.chartData.getLast().value();
		} else {
			throw new RatioComputationException("Unable to calculate CAGR...");
		}
	}

	public void addTimeValue(
		String theTime,
		Double theValue,
		Double theQuantity
	) {
		chartData.add(new TimeValue(theTime, theValue, theQuantity));
		setChartSize(this.chartData.size());
	}
}
