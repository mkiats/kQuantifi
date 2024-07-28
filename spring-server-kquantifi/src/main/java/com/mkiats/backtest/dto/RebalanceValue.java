package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.Setter;

public record RebalanceValue(double beforeValue, double afterValue) {}
