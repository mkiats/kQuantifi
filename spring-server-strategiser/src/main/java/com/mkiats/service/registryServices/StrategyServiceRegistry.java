package com.mkiats.service.registryServices;

import com.mkiats.service.registryServices.strategyServices.StrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StrategyServiceRegistry {

    private final Map<String, StrategyService> strategyServices = new HashMap<>();

    @Autowired
    public StrategyServiceRegistry(List<StrategyService> strategyServiceList) {
        strategyServiceList.forEach(service -> strategyServices.put(service.getClass().getSimpleName(), service));
    }

    public StrategyService getService(String serviceName) {
        return strategyServices.get(serviceName);
    }
}
