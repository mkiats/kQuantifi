package com.mkiats.backtest.service.strategy.investment.interfaces;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import java.util.HashMap;

public interface InvestmentStrategy {
	InvestmentOutput executeStrategy(
		BacktestRequest backtestRequest,
		TimeSeriesStockData timeSeriesStockData
	);
}
