package com.mkiats.backtest.service.strategy.investment.interfaces;

import java.util.HashMap;

public interface InvestmentParameter {
	void deserialise(HashMap<String, Object> theHashmap);
}
