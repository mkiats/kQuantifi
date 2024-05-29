package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacktestRequest {

	private Portfolio[] portfolios;
}
