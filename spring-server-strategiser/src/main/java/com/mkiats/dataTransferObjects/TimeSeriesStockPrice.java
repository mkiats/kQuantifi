package com.mkiats.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TimeSeriesStockPrice {

	private String open;
	private String high;
	private String low;
	private String close;
	private String adjustedClose;
	private String volume;
	private String dividendAmount;

	@JsonCreator
	public TimeSeriesStockPrice(
		@JsonProperty("1. open") String open,
		@JsonProperty("2. high") String high,
		@JsonProperty("3. low") String low,
		@JsonProperty("4. close") String close,
		@JsonProperty("5. adjusted close") String adjustedClose,
		@JsonProperty("6. volume") String volume,
		@JsonProperty("7. dividend amount") String dividendAmount
	) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjustedClose = adjustedClose;
		this.volume = volume;
		this.dividendAmount = dividendAmount;
	}
}
