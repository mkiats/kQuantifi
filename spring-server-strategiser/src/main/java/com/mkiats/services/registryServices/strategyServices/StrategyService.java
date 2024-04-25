package com.mkiats.services.registryServices.strategyServices;

import com.mkiats.dataTransferObjects.TimeSeriesStockData;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.DollarCostAverageParameter;
import com.mkiats.services.registryServices.strategyServices.strategyServicesParameter.StrategyServiceParameter;

public interface StrategyService {

	double executeStrategy();

}
