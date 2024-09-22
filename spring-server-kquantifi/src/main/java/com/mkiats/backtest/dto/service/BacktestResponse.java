package com.mkiats.backtest.dto.service;

import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacktestResponse {

	private InvestmentOutput investmentOutput;
	private FinancialRatioOutput financialRatioOutput;
}
