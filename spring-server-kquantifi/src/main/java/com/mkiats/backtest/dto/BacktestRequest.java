package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacktestRequest {

	private String tickerName;
	private String frequency;
	private String startDate;
	private String endDate;
	private double periodicAmount;
	private double leverageFactor;
	private double desiredStrategy;
}
