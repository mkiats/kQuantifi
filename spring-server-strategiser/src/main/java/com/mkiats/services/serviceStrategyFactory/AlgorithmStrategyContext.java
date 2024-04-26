package com.mkiats.services.serviceStrategyFactory;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class AlgorithmStrategyContext {

	private TimeSeriesStockData timeSeriesStockData;
	private String valueAvg_benchmark;
	private double valueAvg_benchmarkRate;

	public void mapFormDataToAttr() {
		// Pass in FormData object (From frontend) to map to the attributes
	}
}
