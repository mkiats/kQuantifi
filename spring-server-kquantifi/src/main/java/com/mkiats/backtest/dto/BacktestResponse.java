package com.mkiats.backtest.dto;

import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacktestResponse {

	//	private ArrayList<PortfolioOutput> portfolioOutputs = new ArrayList<>();
	private PortfolioQuery portfolioQuery;
	private InvestmentOutput investmentOutput;
	private FinancialRatioOutput financialRatioOutput;
}
