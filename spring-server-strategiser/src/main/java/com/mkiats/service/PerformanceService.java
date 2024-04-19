package com.mkiats.service;

import com.mkiats.service.registryServices.AlgorithmServiceRegistry;
import com.mkiats.service.registryServices.StrategyServiceRegistry;
import com.mkiats.service.registryServices.algorithmServices.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    public final StrategyServiceRegistry strategyServiceRegistry;
    public final AlgorithmServiceRegistry algorithmServiceRegistry;

}
