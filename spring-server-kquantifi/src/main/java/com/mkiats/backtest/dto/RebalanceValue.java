package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.Setter;

public record RebalanceValue(
	String tickerName,
	double beforeValue,
	double afterValue
) {}
