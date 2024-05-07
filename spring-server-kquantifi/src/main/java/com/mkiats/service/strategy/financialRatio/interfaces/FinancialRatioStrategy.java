package com.mkiats.service.strategy.financialRatio.interfaces;

import com.mkiats.service.strategy.investment.InvestmentOutput;

public interface FinancialRatioStrategy {
	double computeRatio(InvestmentOutput investmentOutput);
}
