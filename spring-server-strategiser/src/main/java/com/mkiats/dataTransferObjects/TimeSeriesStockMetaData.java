package com.mkiats.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeSeriesStockMetaData {

	private String information;
	private String symbol;
	private String lastRefreshed;
	private String timeZone;

	@JsonCreator
	public TimeSeriesStockMetaData(
		@JsonProperty("1. Information") String information,
		@JsonProperty("2. Symbol") String symbol,
		@JsonProperty("3. Last Refreshed") String lastRefreshed,
		@JsonProperty("4. Time Zone") String timeZone
	) {
		this.information = information;
		this.symbol = symbol;
		this.lastRefreshed = lastRefreshed;
		this.timeZone = timeZone;
	}
}
