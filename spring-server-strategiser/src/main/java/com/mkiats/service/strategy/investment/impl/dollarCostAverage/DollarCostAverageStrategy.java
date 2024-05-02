package com.mkiats.service.strategy.investment.impl.dollarCostAverage;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.dataTransferObjects.TimeSeriesStockPrice;
import com.mkiats.service.strategy.investment.InvestmentStrategyContext;
import com.mkiats.service.strategy.investment.InvestmentStrategyOutput;
import com.mkiats.service.strategy.investment.interfaces.InvestmentParameter;
import com.mkiats.service.strategy.investment.interfaces.InvestmentStrategy;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DollarCostAverageStrategy implements InvestmentStrategy {

	private DollarCostAverageParameter theParameter =
		new DollarCostAverageParameter();

	@Override
	public List<InvestmentStrategyOutput> executeStrategy(
		HashMap<String, Object> hashmapParameters
	) {
		this.theParameter.deserialise(hashmapParameters);
		TimeSeriesStockData context =
			this.theParameter.getTimeSeriesStockData();
		double dcaAmount = this.theParameter.getPeriodicAmount();
		List<InvestmentStrategyOutput> resList = new ArrayList<>();

		SequencedSet<String> keyList = context
			.getPriceList()
			.sequencedKeySet()
			.reversed();
		for (String dateKey : keyList) {
			double currentAmountInDca = 0;
			double currentStockQuantity = 0;
			TimeSeriesStockPrice timeSeriesStockPrice = context
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
			InvestmentStrategyOutput res = new InvestmentStrategyOutput(
				dateKey,
				currentAmountInDca,
				currentStockQuantity
			);
			resList.add(res);
		}
		return resList;
	}
}
