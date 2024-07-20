package com.mkiats.backtest.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioTickers {

	private List<String> tickerList;
	private List<Double> allocationWeightList;
	private List<Double> leverageFactor;
	private double tickerCount;
}
