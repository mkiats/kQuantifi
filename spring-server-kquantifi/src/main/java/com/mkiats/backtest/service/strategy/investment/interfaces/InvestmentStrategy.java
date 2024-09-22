package com.mkiats.backtest.service.strategy.investment.interfaces;

import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;

public interface InvestmentStrategy {
	InvestmentOutput executeStrategy(BacktestRequest theBacktestRequest);
}
