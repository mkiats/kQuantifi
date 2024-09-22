package com.mkiats.backtest.service.strategy.investment;

import com.mkiats.backtest.dto.service.RebalanceValue;
import com.mkiats.backtest.dto.service.TimeValue;
import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<String, ArrayList<RebalanceValue>> rebalanceData =
		new HashMap<>(); // <timestamp, RebalanceValue>

	public void addOverallTimeValue(
		String theTime,
		Double theValue,
		Double theQuantity
	) {
		this.chartData.add(new TimeValue(theTime, theValue, theQuantity));
		this.setChartSize(this.chartData.size());
	}

	public void addTickerTimeValue(
		String assetName,
		String theTime,
		Double theValue,
		Double theQuantity
	) {
		if (!(assetData.containsKey(assetName))) {
			assetData.put(assetName, new ArrayList<>());
		}
		this.assetData.get(assetName).addLast(
				new TimeValue(theTime, theValue, theQuantity)
			);
	}

	public void addRebalanceTimeValue(
		String timestamp,
		String tickerName,
		double beforeValue,
		double afterValue
	) {
		RebalanceValue rebalanceValue = new RebalanceValue(
			tickerName,
			beforeValue,
			afterValue
		);
		if (!(rebalanceData.containsKey(timestamp))) {
			rebalanceData.put(timestamp, new ArrayList<>());
		}

		this.rebalanceData.get(timestamp).addLast(rebalanceValue);
	}
}
