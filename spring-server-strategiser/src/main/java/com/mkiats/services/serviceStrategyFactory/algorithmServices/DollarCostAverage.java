package com.mkiats.services.serviceStrategyFactory.algorithmServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.services.serviceStrategyFactory.AlgorithmStrategyContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DollarCostAverage implements AlgorithmStrategy {

	@Override
	public double executeStrategy(AlgorithmStrategyContext context) {
		double currentAmountInDca = 0;
		double currentStockQuantity = 0;
		int dcaAmount = 100;

		SequencedSet<String> keyList = context
			.getTimeSeriesStockData()
			.getPriceList()
			.sequencedKeySet()
			.reversed();
		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = context
				.getTimeSeriesStockData()
				.getPriceList()
				.get(dateKey);
			double closingPrice = Double.parseDouble(
				timeSeriesStockPrice.getAdjustedClose()
			);
			double dcaQuantity = BigDecimal.valueOf(dcaAmount / closingPrice)
				.round(new MathContext(4, RoundingMode.HALF_EVEN))
				.doubleValue();
			currentStockQuantity = currentStockQuantity + dcaQuantity;
			currentAmountInDca = currentAmountInDca + dcaAmount;
		}
		return currentAmountInDca / currentStockQuantity;
	}
}
