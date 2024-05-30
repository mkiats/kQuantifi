package com.mkiats.backtest.service.strategy.investment.impl;

import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.dto.Portfolio;
import com.mkiats.backtest.service.strategy.investment.InvestmentOutput;
import com.mkiats.backtest.service.strategy.investment.interfaces.InvestmentStrategy;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.commons.dataTransferObjects.TimeSeriesStockPrice;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.SequencedSet;

import com.mkiats.commons.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
@Setter
public class DollarCostAverage implements InvestmentStrategy {

	private InvestmentOutput theOutput;

	@Override
	public InvestmentOutput executeStrategy(
		Portfolio thePortfolio,
		String earliestCommonInceptionDate
	) {
		System.out.println("Computing DollarCostAverage...");
		this.theOutput = new InvestmentOutput();

		double currentAmountInDca = 0;
		double currentStockQuantity = 0;
		SequencedSet<String> keyList = timeSeriesStockData
			.getPriceList()
			.sequencedKeySet()
			.reversed();
		for (String dateKey : keyList) {
			LocalDateTime localDateTimeKey = DateUtils.convertStringToLocalDate(dateKey, "yyyy-MM-dd").atStartOfDay();
			if (localDateTimeKey.isAfter(DateUtils.convertStringToLocalDateTime(backtestParameters.getStartDate(), "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)")) &&
				localDateTimeKey.isBefore(DateUtils.convertStringToLocalDateTime(backtestParameters.getEndDate(), "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)"))
			) {
				TimeSeriesStockPrice timeSeriesStockPrice = timeSeriesStockData
						.getPriceList()
						.get(dateKey);
				double closingPrice = Double.parseDouble(
						timeSeriesStockPrice.getAdjustedClose()
				);
				double dcaQuantity = BigDecimal.valueOf(periodicAmount / closingPrice)
						.round(new MathContext(4, RoundingMode.HALF_EVEN))
						.doubleValue();
				currentAmountInDca = currentStockQuantity * closingPrice;
				currentAmountInDca = currentAmountInDca + periodicAmount;
				currentStockQuantity = currentStockQuantity + dcaQuantity;
				this.theOutput.addQuantity(currentStockQuantity);
				this.theOutput.addTimeValue(dateKey, currentAmountInDca);
			}
		}
		this.theOutput.setTimeframe(backtestParameters.getFrequency());
		return this.theOutput;
	}
}
