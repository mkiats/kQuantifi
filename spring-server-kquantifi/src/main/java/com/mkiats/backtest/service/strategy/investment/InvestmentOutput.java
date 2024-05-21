package com.mkiats.backtest.service.strategy.investment;

import java.util.ArrayList;

import com.mkiats.commons.dataTransferObjects.TimeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvestmentOutput {

	private ArrayList<String> stockTimestamp = new ArrayList<>();
	private ArrayList<Double> stockAdjustedValue = new ArrayList<>();
	private ArrayList<Double> stockAdjustedQuantity = new ArrayList<>();
	private ArrayList<TimeValue> chartData = new ArrayList<>();
	private int chartSize = 0;
	private Double investedAmount = 0.0;
	private Double periodicAmount = 0.0;
	private String timeframe = "monthly";

	public InvestmentOutput addTimestamp(String curTimestamp) {
		this.stockTimestamp.add(curTimestamp);
		return this;
	}

	public InvestmentOutput addValue(Double curStockValue) {
		this.stockAdjustedValue.add(curStockValue);
		return this;
	}

	public InvestmentOutput addQuantity(Double curStockQuantity) {
		this.stockAdjustedQuantity.add(curStockQuantity);
		return this;
	}

	public InvestmentOutput addInvestedAmount(Double dcaAmount) {
		setPeriodicAmount(dcaAmount);
		setInvestedAmount(this.getInvestedAmount() + dcaAmount);
		return this;
	}

	public void addTimeValue(String theTime, Double theValue) {
		chartData.add(new TimeValue(theTime, theValue));
		setChartSize(this.chartData.size());
	}
}


