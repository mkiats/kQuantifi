package com.mkiats.services.registryServices;

import com.mkiats.services.registryServices.algorithmServices.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AlgorithmServiceRegistry {

    private final Map<String, AlgorithmService> algorithmServices = new HashMap<>();

    @Autowired
    public AlgorithmServiceRegistry(List<AlgorithmService> algorithmServiceList) {
        algorithmServiceList.forEach(service -> algorithmServices.put(service.getClass().getSimpleName(), service));
    }

    public AlgorithmService getService(String serviceName) {
        return algorithmServices.get(serviceName);
    }
}
