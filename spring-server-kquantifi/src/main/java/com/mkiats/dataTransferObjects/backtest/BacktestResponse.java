package com.mkiats.dataTransferObjects.backtest;

import com.mkiats.service.strategy.financialRatio.FinancialRatioOutput;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacktestResponse {

	private InvestmentOutput investmentOutput;
	private FinancialRatioOutput financialRatioOutput;
}
