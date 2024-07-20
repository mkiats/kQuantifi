package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioQuery {

	private PortfolioSettings portfolioSettings;
	private PortfolioTickers portfolioTickers;
}
