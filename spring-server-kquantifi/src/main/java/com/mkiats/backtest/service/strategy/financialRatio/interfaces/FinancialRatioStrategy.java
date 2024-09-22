package com.mkiats.backtest.service.strategy.financialRatio.interfaces;

import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.List;

public interface FinancialRatioStrategy {
	FinancialRatioOutput computeRatio(
		BacktestRequest backtestRequest,
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	);
	List<String> getRatioDependencies();
}
