package com.mkiats.backtest.service.strategy.financialRatio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialRatioOutput {

	private double cagr = 0;
	private MaxDrawdownArray maxDrawdownArray = new MaxDrawdownArray();
	private double sharpeRatio = 0;
	private double sortinoRatio = 0;
	private double standardDeviation = 0;
}

@Getter
@Setter
class MaxDrawdownArray {

	private List<String> timestamp = new ArrayList<>();
	private List<Double> drawdownValue = new ArrayList<>();
	private String startDateOfWorstDrawdown;
	private String endDateOfWorstDrawdown;
	private double worstDrawdownValue;

	public MaxDrawdownArray addTimestamp(String theTimestamp) {
		this.timestamp.add(theTimestamp);
		return this;
	}

	public MaxDrawdownArray addDrawdownValue(Double theDrawdownValue) {
		this.drawdownValue.add(theDrawdownValue);
		return this;
	}
}
