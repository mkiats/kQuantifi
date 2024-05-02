package com.mkiats.service.strategy.investment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InvestmentOutput {

	private List<String> stockTimestamp = new ArrayList<>();
	private List<Double> stockValue = new ArrayList<>();
	private List<Double> stockQuantity = new ArrayList<>();
	private String timeframe = "monthly";

	public InvestmentOutput addTimestamp(String curTimestamp) {
		this.stockTimestamp.add(curTimestamp);
		return this;
	}public InvestmentOutput addValue(Double curStockValue) {
		this.stockValue.add(curStockValue);
		return this;
	}public InvestmentOutput addQuantity(Double curStockQuantity) {
		this.stockQuantity.add(curStockQuantity);
		return this;
	}
}
