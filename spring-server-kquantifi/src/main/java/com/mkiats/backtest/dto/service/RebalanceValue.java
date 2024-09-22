package com.mkiats.backtest.dto.service;

public record RebalanceValue(
	String tickerName,
	double beforeValue,
	double afterValue
) {}
