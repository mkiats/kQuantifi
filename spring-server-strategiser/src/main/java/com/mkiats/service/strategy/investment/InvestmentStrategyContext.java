package com.mkiats.service.strategy.investment;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class InvestmentStrategyContext {

	private TimeSeriesStockData timeSeriesStockData;
	private String valueAvg_target;
	private double valueAvg_targetRate;
	private double valueAvg_periodicAmount;

	public void mapFormDataToAttr() {
		// Pass in FormData object (From frontend) to map to the attributes
	}
}
