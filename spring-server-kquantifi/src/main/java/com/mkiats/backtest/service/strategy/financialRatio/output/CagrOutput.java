package com.mkiats.backtest.service.strategy.financialRatio.output;

import com.mkiats.backtest.exceptions.RatioComputationException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CagrOutput {

	private List<String> timestampArr = new ArrayList<>();
	private List<Double> cagrArr = new ArrayList<>();
	private String bestCagr;
	private String worstCagr;

	public CagrOutput addTimestamp(String theTimestamp) {
		this.timestampArr.add(theTimestamp);
		return this;
	}

	public CagrOutput addDrawdownValue(Double theCagrValue) {
		this.cagrArr.add(theCagrValue);
		return this;
	}

	public double getCagr() {
		if (!cagrArr.isEmpty()) {
			return this.cagrArr.getLast();
		} else {
			throw new RatioComputationException("Unable to calculate CAGR...");
		}
	}
}
