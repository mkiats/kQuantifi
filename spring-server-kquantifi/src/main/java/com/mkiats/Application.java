package com.mkiats;

import com.mkiats.backtest.dto.*;
import com.mkiats.backtest.service.BacktestService;
import com.mkiats.commons.utils.PrettyJson;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		//		BacktestRequest tempRequest = new BacktestRequest();
		//		this.generateDummyBacktestRequest(tempRequest);
		//		BacktestResponse tempResponse = backtestService.doExecute(tempRequest);
		//
		//		try {
		//			PrettyJson.prettyPrintJson(tempResponse);
		//			System.out.println("Pretty print done...");
		//		} catch (Exception e) {
		//			throw new RuntimeException("Json pretty print failed...");
		//		}
		//		System.out.println();
	}

	public void generateDummyBacktestRequest(
		BacktestRequest theBacktestRequest
	) {
		Portfolio portfolio = new Portfolio();
		PortfolioQuery portfolioQuery = new PortfolioQuery();
		PortfolioTickers portfolioTickers = new PortfolioTickers();
		PortfolioSettings portfolioSettings = new PortfolioSettings();

		portfolioSettings.setId("0");
		portfolioSettings.setPortfolioName("Test portfolio");
		portfolioSettings.setFrequency("MONTHLY");
		portfolioSettings.setInvestmentStrategy("DollarCostAverage");
		portfolioSettings.setStartDate("2022-01-01");
		portfolioSettings.setEndDate("2023-12-31");
		portfolioSettings.setPeriodicCashflow(1000);
		portfolioSettings.setInitialBalance(1000);
		portfolio.setPortfolioSettings(portfolioSettings);

		portfolioTickers.setTickerCount(2);
		ArrayList<Double> levArr = new ArrayList<>();
		levArr.add(1.0);
		levArr.add(1.0);
		portfolioTickers.setLeverageFactor(levArr);
		ArrayList<Double> weightArr = new ArrayList<>();
		weightArr.add(50.0);
		weightArr.add(50.0);
		portfolioTickers.setAllocationWeightList(weightArr);
		ArrayList<String> tickerArr = new ArrayList<>();
		tickerArr.add("SPY");
		tickerArr.add("QQQ");
		portfolioTickers.setTickerList(tickerArr);
		portfolio.setPortfolioTickers(portfolioTickers);

		theBacktestRequest.setPortfolio(portfolio);
		theBacktestRequest.setPortfolioQuery(portfolioQuery);
	}
}
