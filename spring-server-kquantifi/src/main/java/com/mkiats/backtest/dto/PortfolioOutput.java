package com.mkiats.backtest.dto;

import com.mkiats.backtest.service.strategy.financialRatio.output.FinancialRatioOutput;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioOutput {

    private InvestmentOutput investmentOutput;
    private FinancialRatioOutput financialRatioOutput;
}
