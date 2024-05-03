package com.mkiats.service.strategy.investment.impl.dollarCostAverage;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.service.strategy.investment.InvestmentOutput;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
@Setter
public class DollarCostAverageStrategy implements InvestmentStrategy {

	private DollarCostAverageParameter theParameter =
		new DollarCostAverageParameter();

	private InvestmentOutput theOutput = new InvestmentOutput();

	@Override
	public InvestmentOutput executeStrategy(
		HashMap<String, Object> hashmapParameters
	) {
		this.theParameter.deserialise(hashmapParameters);
		TimeSeriesStockData stockData =
			this.theParameter.getTimeSeriesStockData();
		double dcaAmount = this.theParameter.getPeriodicAmount();
		this.theOutput = new InvestmentOutput();

		double currentAmountInDca = 0;
		double currentStockQuantity = 0;
		SequencedSet<String> keyList = stockData
			.getPriceList()
			.sequencedKeySet()
			.reversed();
		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = stockData
				.getPriceList()
				.get(dateKey);
			double closingPrice = Double.parseDouble(
				timeSeriesStockPrice.getAdjustedClose()
			);
			double dcaQuantity = BigDecimal.valueOf(dcaAmount / closingPrice)
				.round(new MathContext(4, RoundingMode.HALF_EVEN))
				.doubleValue();
			currentAmountInDca = currentStockQuantity * closingPrice;
			currentAmountInDca = currentAmountInDca + dcaAmount;
			currentStockQuantity = currentStockQuantity + dcaQuantity;
			this.theOutput.addTimestamp(dateKey)
				.addValue(currentAmountInDca)
				.addQuantity(currentStockQuantity)
				.addInvestedAmount(dcaAmount);
		}
		return this.theOutput;
	}
}
