package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class RebalanceOutput {
    private HashMap<String, AssetRebalanceLog> assetRebalanceLogs;
    // <Timestamp, AssetRebalanceLog>
}
