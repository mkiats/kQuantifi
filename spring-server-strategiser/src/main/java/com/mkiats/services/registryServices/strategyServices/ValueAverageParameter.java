package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValueAverageParameter {
    private TimeSeriesStockData timeSeriesStockData;
    private double benchmarkRate;
}
