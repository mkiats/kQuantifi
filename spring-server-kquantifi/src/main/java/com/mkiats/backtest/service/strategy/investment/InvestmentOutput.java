package com.mkiats.backtest.service.strategy.investment;

import java.util.ArrayList;
import java.util.HashMap;

import com.mkiats.commons.dataTransferObjects.TimeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvestmentOutput {

	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private HashMap<String, ArrayList<TimeValue>> assetData = new HashMap<>();

	public void addTimeValue(String theTime, Double theValue) {
		this.chartData.add(new TimeValue(theTime, theValue));
		setChartSize(this.chartData.size());
	}

	public void addAssetTimeValue(String assetName, String theTime, Double theValue) {
		this.assetData.get(assetName).addLast(new TimeValue(theTime, theValue));
	}
}


