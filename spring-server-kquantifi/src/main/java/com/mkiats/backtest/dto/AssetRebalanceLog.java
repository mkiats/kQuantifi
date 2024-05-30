package com.mkiats.backtest.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
@Getter
@Setter
@NoArgsConstructor
public class AssetRebalanceLog {

    private HashMap<String, RebalanceValue> rebalanceValues;
    // <AssetName, RebalanceValue>
}
