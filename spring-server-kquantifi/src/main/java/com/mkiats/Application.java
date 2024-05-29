package com.mkiats;

import com.mkiats.backtest.dto.*;
import com.mkiats.backtest.service.BacktestService;
import com.mkiats.commons.utils.PrettyJson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	private final BacktestService backtestService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("STARTED");


		BacktestRequest tempRequest = new BacktestRequest();
		tempRequest = this.generateDummyBacktestRequest(tempRequest);
		tempRequest = this.generateDummyBacktestRequest2(tempRequest);
		BacktestResponse tempResponse = backtestService.doExecute(tempRequest);
		try {
			PrettyJson.prettyPrintJson(tempRequest);
			System.out.println("Pretty print done...");
		} catch (Exception e) {
			throw new RuntimeException("Json pretty print failed...");
		}
		System.out.println();



	}

	public BacktestRequest generateDummyBacktestRequest(BacktestRequest theBacktestRequest) {
		Asset tempAsset = new Asset();
		tempAsset.setAssetName("SPY");
		tempAsset.setWeightage(100.0);
		tempAsset.setExpenseRatio(0.0);
		tempAsset.setLeverageFactor(0.0);
		ArrayList<Asset> tempAssetList = new ArrayList<>();
		tempAssetList.addLast(tempAsset);
		Portfolio tempPortfolio = new Portfolio();
		tempPortfolio.setId("0");
		tempPortfolio.setPortfolioName("temp portfolio");
		tempPortfolio.setStartDate("2024-04-23");
		tempPortfolio.setEndDate("2023-04-23");
		tempPortfolio.setAssets(tempAssetList);
		tempPortfolio.setFrequency("WEEKLY");
		tempPortfolio.setDesiredStrategy("DollarCostAverage");
		tempPortfolio.setInitialAmount(10);
		tempPortfolio.setPeriodicAmount(1000);
		theBacktestRequest.getPortfolios().addLast(tempPortfolio);
		return theBacktestRequest;
	}

	public BacktestRequest generateDummyBacktestRequest2(BacktestRequest theBacktestRequest) {
		Asset tempAsset = new Asset();
		tempAsset.setAssetName("QQQ");
		tempAsset.setWeightage(100.0);
		tempAsset.setExpenseRatio(0.0);
		tempAsset.setLeverageFactor(0.0);
		ArrayList<Asset> tempAssetList = new ArrayList<>();
		tempAssetList.addLast(tempAsset);
		Portfolio tempPortfolio = new Portfolio();
		tempPortfolio.setId("0");
		tempPortfolio.setPortfolioName("temp portfolio");
		tempPortfolio.setStartDate("2022-04-23");
		tempPortfolio.setEndDate("2023-04-23");
		tempPortfolio.setAssets(tempAssetList);
		tempPortfolio.setFrequency("WEEKLY");
		tempPortfolio.setDesiredStrategy("DollarCostAverage");
		tempPortfolio.setInitialAmount(10);
		tempPortfolio.setPeriodicAmount(1000);
		theBacktestRequest.getPortfolios().addLast(tempPortfolio);
		return theBacktestRequest;
	}
}
