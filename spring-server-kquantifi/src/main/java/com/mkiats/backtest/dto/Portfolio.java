package com.mkiats.backtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Portfolio {

    private String id;
    private String portfolioName;
    private String desiredStrategy;
    private String rebalanceStrategy;
    private double periodicAmount;
    private double initialAmount;
    private String frequency;
    private String startDate;
    private String endDate;
    private ArrayList<Asset> assets;
}
