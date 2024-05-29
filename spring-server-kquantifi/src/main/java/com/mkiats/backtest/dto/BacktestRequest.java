package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class BacktestRequest {

	private ArrayList<Portfolio> portfolios = new ArrayList<>();
}
