package com.mkiats.backtest.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Portfolio {

	private PortfolioSettings portfolioSettings;
	private PortfolioTickers portfolioTickers;
}
