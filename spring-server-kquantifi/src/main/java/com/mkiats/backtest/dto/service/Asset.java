package com.mkiats.backtest.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Asset {

	private String assetName;
	private double weightage;
	private double leverageFactor;
	private double expenseRatio;
}
