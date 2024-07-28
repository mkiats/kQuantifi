package com.mkiats.backtest.service.strategy.investment.impl;

import com.mkiats.backtest.dto.*;
import com.mkiats.backtest.service.RetrievalService;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.entities.TickerPrice;
import com.mkiats.commons.utils.DateUtils;
import com.mkiats.commons.utils.PrettyJson;
import java.util.Arrays;
import java.util.List;
import lombok.*;
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
			quantities[i] = 0;
		}

		// Simulate over time
		String startDate = settings.getStartDate();
		String endDate = settings.getEndDate();
		List<String> temp = retrievalService.fetchTimestamps(
			tickerSymbols[1],
			settings.getFrequency()
		);
		List<String> timeframes = generateTimeframes(temp, startDate, endDate);

		for (String timestamp : timeframes) {
			// Invest periodic cashflow
			investPeriodicCashflow(
				periodicCashflow,
				currentValues,
				quantities,
				targetAllocations,
				tickerSymbols,
				settings.getFrequency(),
				timestamp
			);
			// Check for rebalancing
			rebalancePortfolio(
				periodicCashflow,
				currentValues,
				quantities,
				targetAllocations,
				tickerSymbols,
				settings.getFrequency(),
				timestamp
			);

			for (int i = 0; i < tickerSymbols.length; i++) {
				this.theOutput.addTickerTimeValue(
						tickerSymbols[i],
						timestamp,
						currentValues[i],
						quantities[i]
					);
			}
		}

		try {
			PrettyJson.prettyPrintJson(this.theOutput.getAssetData());
			System.out.println("Json pretty print success...");
		} catch (Exception e) {
			throw new RuntimeException("Json pretty print failed...");
		}

		return this.theOutput;
	}

	private void investPeriodicCashflow(
		double cashflow,
		double[] currentValues,
		double[] quantities,
		double[] allocationWeights,
		String[] tickerNames,
		String timeframe,
		String timestamp
	) {
		double overallValue = 0;
		for (int i = 0; i < tickerNames.length; i++) {
			double tickerCashflow = cashflow * allocationWeights[i];
			TickerPrice tickerPrice = retrievalService.fetchTickerDataFromDb(
				tickerNames[i],
				timeframe,
				timestamp
			);
			quantities[i] += tickerCashflow / tickerPrice.getAdjustedClose();
			currentValues[i] = quantities[i] * tickerPrice.getAdjustedClose();
			overallValue += currentValues[i];
		}
		this.theOutput.addOverallTimeValue(timestamp, overallValue, 0.0);
	}

	private void rebalancePortfolio(
		double cashflow,
		double[] currentValues,
		double[] quantities,
		double[] allocationWeights,
		String[] tickerNames,
		String timeframe,
		String timestamp
	) {
		double totalValue = this.theOutput.getChartData().getLast().value();
		boolean needsRebalance = false;

		for (int i = 0; i < tickerNames.length; i++) {
			double currentAllocation = currentValues[i] / totalValue;
			if (Math.abs(currentAllocation - allocationWeights[i]) > 0.05) { // 5% threshold
				needsRebalance = true;
				break;
			}
		}
		if (needsRebalance) {
			for (int i = 0; i < tickerNames.length; i++) {
				double targetValue = totalValue * allocationWeights[i];
				TickerPrice tickerPrice =
					retrievalService.fetchTickerDataFromDb(
						tickerNames[i],
						timeframe,
						timestamp
					);
				this.theOutput.addRebalanceTimeValue(
						timestamp,
						tickerNames[i],
						currentValues[i],
						targetValue
					);
				double difference = targetValue - currentValues[i];
				quantities[i] += (difference / tickerPrice.getAdjustedClose());
				currentValues[i] = targetValue;
			}
		}
	}

	private List<String> generateTimeframes(
		List<String> dates,
		String startDate,
		String endDate
	) {
		int left = DateUtils.binarySearchDate(dates, startDate);
		int right = DateUtils.binarySearchDate(dates, endDate);
		return dates.subList(left, right);
	}
}
