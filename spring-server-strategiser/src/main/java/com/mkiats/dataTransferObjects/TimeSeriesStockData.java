package com.mkiats.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class TimeSeriesStockData {
    private TimeSeriesStockMetaData metaData;
    private Map<String, TimeSeriesStockPrice> priceList;

    @JsonCreator
    public TimeSeriesStockData(@JsonProperty("Meta Data") TimeSeriesStockMetaData metaData,
                               @JsonProperty("Monthly Adjusted Time Series") Map<String, TimeSeriesStockPrice> priceList) {
        this.metaData = metaData;
        this.priceList = priceList;
    }
}

