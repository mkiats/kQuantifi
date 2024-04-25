package com.mkiats.services.registryServices.strategyServices.strategyServicesParameter;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.strategyServices.StrategyService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@NoArgsConstructor
public class DollarCostAverageParameter implements StrategyServiceParameter {
    private TimeSeriesStockData timeSeriesStockData;

    @Override
    public void convertFormToParameter() {

    }
}
