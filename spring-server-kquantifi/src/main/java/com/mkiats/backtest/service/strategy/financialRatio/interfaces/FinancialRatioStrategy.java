package com.mkiats.backtest.service.strategy.financialRatio.interfaces;

import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;

public interface FinancialRatioStrategy {
	double computeRatio(InvestmentOutput investmentOutput);
}
