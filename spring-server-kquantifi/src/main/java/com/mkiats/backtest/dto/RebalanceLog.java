package com.mkiats.backtest.dto;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RebalanceLog {

	private HashMap<String, RebalanceValue> rebalanceValues;
	// <AssetName, RebalanceValue>
}
