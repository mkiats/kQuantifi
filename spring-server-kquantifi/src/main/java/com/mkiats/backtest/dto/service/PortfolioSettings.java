package com.mkiats.backtest.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioSettings {

	private String id;
	private String portfolioName;
	private String investmentStrategy;
	private String rebalanceStrategy;
	private double initialBalance;
	private double periodicCashflow;
	private String frequency;
	private String startDate;
	private String endDate;
}
