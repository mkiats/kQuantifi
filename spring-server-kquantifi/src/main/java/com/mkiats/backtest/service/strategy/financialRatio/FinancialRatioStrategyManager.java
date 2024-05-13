package com.mkiats.backtest.service.strategy.financialRatio;

import com.mkiats.backtest.exceptions.RatioDependencyCycleException;
import com.mkiats.backtest.service.strategy.financialRatio.interfaces.FinancialRatioStrategy;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialRatioStrategyManager {

	private final Map<String, FinancialRatioStrategy> financialRatioMap =
		new HashMap<>();

	private List<String> sortedFinancialRatioList = new ArrayList<>();

	@Autowired // Construction injection
	public FinancialRatioStrategyManager(
		List<FinancialRatioStrategy> strategyServiceList
	) {
		strategyServiceList.forEach(service -> {
			this.financialRatioMap.put(
					service.getClass().getSimpleName(),
					service
				);
		});
		this.sortedFinancialRatioList = topologicalSort(strategyServiceList);
	}

	public FinancialRatioStrategy getService(String serviceName) {
		return financialRatioMap.get(serviceName);
	}

	public List<String> getAllServices() {
		return sortedFinancialRatioList;
	}

	private List<String> topologicalSort(
		List<FinancialRatioStrategy> strategyServiceList
	) {
		Map<String, Integer> inDegree = new HashMap<>();
		Map<String, List<String>> graph = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		List<String> sortedOrder = new ArrayList<>();

		// Initialize graph and in-degree count
		for (FinancialRatioStrategy strategy : strategyServiceList) {
			String strategyName = strategy.getClass().getSimpleName();
			inDegree.put(strategyName, 0);
			graph.put(strategyName, new ArrayList<>());
		}

		// Build the graph and update in-degree of each node
		for (FinancialRatioStrategy strategy : strategyServiceList) {
			String strategyName = strategy.getClass().getSimpleName();
			for (String dependency : strategy.getRatioDependencies()) {
				graph.get(dependency).add(strategyName);
				inDegree.put(strategyName, inDegree.get(strategyName) + 1);
			}
		}

		for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}

		// Process the graph
		while (!queue.isEmpty()) {
			String current = queue.poll();
			sortedOrder.add(current);
			for (String neighbor : graph.get(current)) {
				inDegree.put(neighbor, inDegree.get(neighbor) - 1);
				if (inDegree.get(neighbor) == 0) {
					queue.add(neighbor);
				}
			}
		}

		// Check if there was a cycle in the graph
		if (sortedOrder.size() != strategyServiceList.size()) {
			throw new RatioDependencyCycleException(
				"Cycle detected in dependency graph, cannot perform topological sort"
			);
		}

		return sortedOrder;
	}
}
