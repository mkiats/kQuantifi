package com.mkiats.backtest.service.strategy.investment;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestmentOutput {

	private ArrayList<String> stockTimestamp = new ArrayList<>();
	private ArrayList<Double> stockValue = new ArrayList<>();
	private ArrayList<Double> stockQuantity = new ArrayList<>();
	private Double investedAmount = 1.0;
	private String timeframe = "monthly";

	public InvestmentOutput addTimestamp(String curTimestamp) {
		this.stockTimestamp.add(curTimestamp);
		return this;
	}

	public InvestmentOutput addValue(Double curStockValue) {
		this.stockValue.add(curStockValue);
		return this;
	}

	public InvestmentOutput addQuantity(Double curStockQuantity) {
		this.stockQuantity.add(curStockQuantity);
		return this;
	}

	public InvestmentOutput addInvestedAmount(Double dcaAmount) {
		setInvestedAmount(this.getInvestedAmount() + dcaAmount);
		return this;
	}
}
