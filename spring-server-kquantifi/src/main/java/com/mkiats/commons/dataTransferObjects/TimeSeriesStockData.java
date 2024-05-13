package com.mkiats.commons.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TimeSeriesStockData {

	private TimeSeriesStockMetaData metaData;
	private LinkedHashMap<String, TimeSeriesStockPrice> priceList;

	@JsonCreator
	public TimeSeriesStockData(
		@JsonProperty("Meta Data") TimeSeriesStockMetaData metaData,
		@JsonProperty("Monthly Adjusted Time Series") LinkedHashMap<
			String,
			TimeSeriesStockPrice
		> priceList
	) {
		this.metaData = metaData;
		this.priceList = priceList;
	}
}
