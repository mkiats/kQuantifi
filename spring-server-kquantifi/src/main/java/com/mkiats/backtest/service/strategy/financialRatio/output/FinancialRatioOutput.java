package com.mkiats.backtest.service.strategy.financialRatio.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FinancialRatioOutput {

	private double cagr = 0;
	private MaxDrawdownOutput maxDrawdown = new MaxDrawdownOutput();
	private double sharpeRatio = 0;
	private double sortinoRatio = 0;
	private double standardDeviation = 0;

}
