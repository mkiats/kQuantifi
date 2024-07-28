package com.mkiats.backtest.dto;

import java.util.ArrayList;
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
