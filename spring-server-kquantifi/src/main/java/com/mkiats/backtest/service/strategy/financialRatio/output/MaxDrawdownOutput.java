package com.mkiats.backtest.service.strategy.financialRatio.output;

import com.mkiats.backtest.dto.service.TimeValue;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaxDrawdownOutput {

	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private String startDateOfWorstDrawdownIndex;
	private String endDateOfWorstDrawdownIndex;
	private Double worstDrawdownValue;

	public void addTimeValue(
		String theTime,
		Double theValue,
		Double theQuantity
	) {
		chartData.add(new TimeValue(theTime, theValue, theQuantity));
		setChartSize(this.chartData.size());
	}
}
