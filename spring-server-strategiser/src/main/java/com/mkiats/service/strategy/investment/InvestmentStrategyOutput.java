package com.mkiats.service.strategy.investment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InvestmentStrategyOutput {

	private final String currentDate;
	private final double currentStockValue;
	private final double currentStockQuantity;
}
