package com.mkiats.backtest.dto.service;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioTickers {

	private ArrayList<String> tickerList;
	private ArrayList<Double> allocationWeightList;
	private ArrayList<Double> leverageFactor;
	private double tickerCount;
}
