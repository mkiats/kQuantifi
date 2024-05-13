package com.mkiats.backtest.service.strategy.investment.impl;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;

import java.sql.Time;
import java.util.HashMap;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
@Setter
public class ValueAverage implements InvestmentStrategy {

	private InvestmentOutput theOutput = new InvestmentOutput();

	@Override
	public InvestmentOutput executeStrategy(
		BacktestRequest backtestParameter, TimeSeriesStockData timeSeriesStockData
	) {
		System.out.println("Computing ValueAverage...");
		double cashNeeded = 0;
		double currentBal = 0;
		double currentQty = 0;
		double targetBal = 0;
		double previousClose = 0;
		double periodicAmount = backtestParameter.getPeriodicAmount();

		SequencedSet<String> keyList = timeSeriesStockData
			.getPriceList()
			.sequencedKeySet()
			.reversed();

		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = timeSeriesStockData
				.getPriceList()
				.get(dateKey);
			if (previousClose == 0) {
				previousClose = Double.parseDouble(
					timeSeriesStockPrice.getAdjustedClose()
				);
				currentQty = periodicAmount / previousClose;
				currentBal = periodicAmount;
				targetBal = periodicAmount;
				this.theOutput
					.addTimestamp(dateKey)
					.addValue(currentBal)
					.addQuantity(currentQty);
			} else {
				double currentClose = Double.parseDouble(
					timeSeriesStockPrice.getAdjustedClose()
				);
				targetBal = targetBal + periodicAmount;
				double newCurrentBal = currentQty * currentClose;
				double balToBeAdded = targetBal - newCurrentBal;

				if (balToBeAdded > 0) {
					// Need to add
					double qtyToBeAdded = balToBeAdded / currentClose;
					currentQty = currentQty + qtyToBeAdded;
					currentBal = targetBal;
					this.theOutput
						.addTimestamp(dateKey)
						.addValue(currentBal)
						.addQuantity(currentQty);
				} else {
					this.theOutput
						.addTimestamp(dateKey)
						.addValue(currentBal)
						.addQuantity(currentQty);
				}
			}
		}
		return this.theOutput;
	}
}
