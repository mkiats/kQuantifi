package com.mkiats.backtest.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioQuery {

	// TickerSymbol - TickerQuery
	//	private LinkedHashMap<String, TickerQuery> tickerQueryList;
	private String earliestCommonInceptionDate;
}
