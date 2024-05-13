package com.mkiats.backtest.service.strategy.financialRatio.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MaxDrawdownOutput {

        private List<String> timestamp = new ArrayList<>();
        private List<Double> drawdownValue = new ArrayList<>();
        private String startDateOfWorstDrawdown;
        private String endDateOfWorstDrawdown;
        private double worstDrawdownValue;

        public MaxDrawdownOutput addTimestamp(String theTimestamp) {
            this.timestamp.add(theTimestamp);
            return this;
        }

        public MaxDrawdownOutput addDrawdownValue(Double theDrawdownValue) {
            this.drawdownValue.add(theDrawdownValue);
            return this;
        }
}
