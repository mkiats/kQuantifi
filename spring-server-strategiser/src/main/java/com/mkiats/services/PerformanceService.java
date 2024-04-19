package com.mkiats.services;

import com.mkiats.services.registryServices.AlgorithmServiceRegistry;
import com.mkiats.services.registryServices.StrategyServiceRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    public final StrategyServiceRegistry strategyServiceRegistry;
    public final AlgorithmServiceRegistry algorithmServiceRegistry;

}
