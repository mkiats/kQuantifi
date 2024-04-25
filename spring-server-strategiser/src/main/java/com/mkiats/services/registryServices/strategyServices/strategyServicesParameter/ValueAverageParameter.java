package com.mkiats.services.registryServices.strategyServices.strategyServicesParameter;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.strategyServices.StrategyService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValueAverageParameter implements StrategyServiceParameter {
    private TimeSeriesStockData timeSeriesStockData;
    private double benchmarkRate;

    @Override
    public void convertFormToParameter() {

    }
}
