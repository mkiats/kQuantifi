package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.SequencedSet;

import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.DollarCostAverageParameter;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.StrategyServiceParameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DollarCostAverage implements StrategyService {

	// Calculate initial value and final value of the strategy
	/*
    Pseudo Code:

    Initialise currentStockQuantity & currentStockValue

    Iteration:
        - calculate addedStockQuantity by finding (DCA amount / current stock price)
        - Update currentStockQuantity
        - Add DCA amount to currentStockValue
        - Update currentStockValue

    End: Calculate averageEntryPrice
     */

	private DollarCostAverageParameter dollarCostAverageParameter = new DollarCostAverageParameter();

	@Override
	public double executeStrategy() {
		double currentAmountInDca = 0;
		double currentStockQuantity = 0;
		int dcaAmount = 100;

		SequencedSet<String> keyList = this.dollarCostAverageParameter.getTimeSeriesStockData()
			.getPriceList()
			.sequencedKeySet()
			.reversed();
		for (String dateKey : keyList) {
			TimeSeriesStockPrice timeSeriesStockPrice = this.dollarCostAverageParameter.getTimeSeriesStockData()
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
