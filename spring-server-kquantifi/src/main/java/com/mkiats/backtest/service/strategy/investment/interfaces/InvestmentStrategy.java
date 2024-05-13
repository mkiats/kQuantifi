package com.mkiats.backtest.service.strategy.investment.interfaces;

import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.HashMap;

public interface InvestmentStrategy {
	InvestmentOutput executeStrategy(HashMap<String, Object> theParameters);
}
