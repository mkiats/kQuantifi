package com.mkiats.backtest.service.strategy.financialRatio.output;

import com.mkiats.backtest.exceptions.RatioComputationException;
import java.util.ArrayList;
import java.util.List;

import com.mkiats.commons.dataTransferObjects.TimeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CagrOutput {

	private ArrayList<String> timestampArr = new ArrayList<>();
	private ArrayList<Double> cagrArr = new ArrayList<>();
	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private Double bestCagr;
	private Double worstCagr;

	public CagrOutput addTimestamp(String theTimestamp) {
		this.timestampArr.add(theTimestamp);
		return this;
	}

	public CagrOutput addCagrValue(Double theCagrValue) {
		this.cagrArr.add(theCagrValue);
		return this;
	}

	public double getCagr() {
		if (!cagrArr.isEmpty()) {
			return this.cagrArr.getLast();
		} else {
			throw new RatioComputationException("Unable to calculate CAGR...");
		}
	}

	public void addTimeValue(String theTime, Double theValue) {
		chartData.add(new TimeValue(theTime, theValue));
		setChartSize(this.chartData.size());
	}
}
