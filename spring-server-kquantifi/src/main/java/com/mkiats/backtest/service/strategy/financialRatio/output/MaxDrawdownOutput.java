package com.mkiats.backtest.service.strategy.financialRatio.output;

import java.util.ArrayList;
import java.util.List;

import com.mkiats.commons.dataTransferObjects.TimeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaxDrawdownOutput {

	private List<String> timestampArr = new ArrayList<>();
	private List<Double> drawdownArr = new ArrayList<>();
	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private String startDateOfWorstDrawdownIndex;
	private String endDateOfWorstDrawdownIndex;
	private Double worstDrawdownValue;

	public MaxDrawdownOutput addTimestamp(String theTimestamp) {
		this.timestampArr.add(theTimestamp);
		return this;
	}

	public MaxDrawdownOutput addDrawdownValue(Double theDrawdownValue) {
		this.drawdownArr.add(theDrawdownValue);
		return this;
	}

	public void addTimeValue(String theTime, Double theValue) {
		chartData.add(new TimeValue(theTime, theValue));
		setChartSize(this.chartData.size());
	}
}
