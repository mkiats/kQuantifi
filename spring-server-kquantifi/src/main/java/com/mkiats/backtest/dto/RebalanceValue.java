package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record RebalanceValue(double beforeValue, double afterValue) {
}
