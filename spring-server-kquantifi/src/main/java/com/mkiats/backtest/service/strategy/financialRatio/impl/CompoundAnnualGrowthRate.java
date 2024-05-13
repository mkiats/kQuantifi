package com.mkiats.backtest.service.strategy.financialRatio.impl;

import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CompoundAnnualGrowthRate implements FinancialRatioStrategy {

	private final List<String> ratioDependencies = new ArrayList<>();

	// Inputs: {# of periods, initialValue, finalValue}
	@Override
	public FinancialRatioOutput computeRatio(
		InvestmentOutput investmentOutput,
		FinancialRatioOutput financialRatioOutput
	) {
		System.out.println("Computing CAGR...");

		double principal = investmentOutput.getInvestedAmount();
		double finalValue = investmentOutput.getStockValue().getLast();
		double numOfCompoundingPeriods = investmentOutput
			.getStockValue()
			.size();
		// Hardcorded to assume 1 period is 1 month
		// TODO: CAGR to output CagrOutput, computation need take into account frequency, startDate and endDate
		double timePeriod = numOfCompoundingPeriods / 12;

		double cagr =
			((Math.pow(
						(finalValue / principal),
						(1 / numOfCompoundingPeriods)
					) -
					1) *
				100 *
				timePeriod);

		financialRatioOutput.getCagrOutput().addDrawdownValue(cagr);
		return financialRatioOutput;
	}

	@Override
	public List<String> getRatioDependencies() {
		return this.ratioDependencies;
	}
}
