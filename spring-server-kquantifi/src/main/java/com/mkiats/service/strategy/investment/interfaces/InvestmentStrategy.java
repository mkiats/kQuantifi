package com.mkiats.service.strategy.investment.interfaces;

import com.mkiats.service.strategy.investment.InvestmentOutput;
import java.util.HashMap;
import java.util.List;

public interface InvestmentStrategy {
	InvestmentOutput executeStrategy(HashMap<String, Object> theParameters);
}
