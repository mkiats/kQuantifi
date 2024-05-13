package com.mkiats.backtest.service.strategy.financialRatio.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaxDrawdownOutput {

	private List<String> timestampArr = new ArrayList<>();
	private List<Double> drawdownArr = new ArrayList<>();
	private String startDateOfWorstDrawdown;
	private String endDateOfWorstDrawdown;
	private double worstDrawdownValue;

	public MaxDrawdownOutput addTimestamp(String theTimestamp) {
		this.timestampArr.add(theTimestamp);
		return this;
	}

	public MaxDrawdownOutput addDrawdownValue(Double theDrawdownValue) {
		this.drawdownArr.add(theDrawdownValue);
		return this;
	}
}
