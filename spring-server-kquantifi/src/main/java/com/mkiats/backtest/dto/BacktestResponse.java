package com.mkiats.backtest.dto;

import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacktestResponse {

	private InvestmentOutput investmentOutput;
	private FinancialRatioOutput financialRatioOutput;
}
