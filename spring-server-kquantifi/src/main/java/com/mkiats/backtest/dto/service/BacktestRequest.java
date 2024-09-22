package com.mkiats.backtest.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacktestRequest {

	private Portfolio portfolio;
	private PortfolioQuery portfolioQuery;
}
