package com.mkiats.backtest.service.strategy.investment.impl;

import com.mkiats.backtest.dto.*;
import com.mkiats.backtest.service.RetrievalService;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.commons.temp.TempClass;
import com.mkiats.commons.utils.DateUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SequencedSet;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DollarCostAverage implements InvestmentStrategy {

	private InvestmentOutput theOutput;
	// Constructor injection via @RequiredArgsConstructor
	private final RetrievalService retrievalService;

	@Override
	public InvestmentOutput executeStrategy(
		BacktestRequest theBacktestRequest
	) {
		System.out.println("Computing DollarCostAverage...");
		this.theOutput = new InvestmentOutput();

		PortfolioSettings settings = theBacktestRequest
			.getPortfolio()
			.getPortfolioSettings();
		PortfolioTickers tickers = theBacktestRequest
			.getPortfolio()
			.getPortfolioTickers();

		double initialBalance = settings.getInitialBalance();
		double periodicCashflow = settings.getPeriodicCashflow();
		double[] targetAllocations = tickers
			.getAllocationWeightList()
			.stream()
			.mapToDouble(Double::doubleValue)
			.toArray();
		String[] tickerSymbols = tickers.getTickerList().toArray(new String[0]);

		// Initialize current values and quantities
		double[] currentValues = new double[tickerSymbols.length];
		double[] quantities = new double[tickerSymbols.length];
		for (int i = 0; i < tickerSymbols.length; i++) {
			targetAllocations[i] = targetAllocations[i] / 100;
			currentValues[i] = initialBalance * targetAllocations[i];
		}

		// Simulate over time
		String startDate = settings.getStartDate();
		String endDate = settings.getEndDate();
		List<String> timeframes = generateTimeframes(
			startDate,
			endDate,
			settings.getFrequency()
		);

		for (String time : timeframes) {
			// Invest periodic cashflow
			investPeriodicCashflow(
				periodicCashflow,
				currentValues,
				quantities,
				targetAllocations,
				tickerSymbols,
				time
			);

			// Check for rebalancing
			rebalancePortfolio(
				targetAllocations,
				currentValues,
				quantities,
				tickerSymbols,
				time
			);
		}

		return this.theOutput;
	}

	private void investPeriodicCashflow(
		double cashflow,
		double[] currentValues,
		double[] quantities,
		double[] allocationWeights,
		String[] tickerSymbols,
		String time
	) {
		for (int i = 0; i < tickerSymbols.length; i++) {
			double investment = cashflow / allocationWeights[i];
			double price = getPrice(tickerSymbols[i], time);
			quantities[i] += investment / price;
			currentValues[i] += investment;
			this.theOutput.addAssetTimeValue(
					tickerSymbols[i],
					time,
					currentValues[i]
				);
		}
		this.theOutput.addTimeValue(time, Arrays.stream(currentValues).sum());
	}

	private void rebalancePortfolio(
		double[] targetAllocations,
		double[] currentValues,
		double[] quantities,
		String[] tickerSymbols,
		String time
	) {
		double totalValue = Arrays.stream(currentValues).sum();
		boolean needsRebalance = false;

		for (int i = 0; i < tickerSymbols.length; i++) {
			double currentAllocation = currentValues[i] / totalValue;
			if (Math.abs(currentAllocation - targetAllocations[i]) > 0.05) { // 5% threshold
				needsRebalance = true;
				break;
			}
		}

		if (needsRebalance) {
			for (int i = 0; i < tickerSymbols.length; i++) {
				double targetValue = totalValue * targetAllocations[i];
				double price = getPrice(tickerSymbols[i], time);
				quantities[i] = targetValue / price;
				currentValues[i] = targetValue;
				this.theOutput.getRebalanceData()
					.computeIfAbsent(tickerSymbols[i], k -> new ArrayList<>())
					.add(new RebalanceLog(time, currentValues[i]));
			}
		}
	}

	private double getPrice(String ticker, String time) {
		// Placeholder for retrieving price from TickerPrice data
		return 100.0; // Replace with actual logic to fetch price
	}

	private List<String> generateTimeframes(
		String startDate,
		String endDate,
		String frequency
	) {
		// Placeholder for generating timeframes based on frequency
		return Arrays.asList(startDate, endDate); // Replace with actual logic
	}
}
