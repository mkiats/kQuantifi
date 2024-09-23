export interface BacktestRequest {
	portfolio: Portfolio;
}

export interface Portfolio {
	portfolioSettings: PortfolioSettings;
	portfolioTickers: PortfolioTickers;
}

export interface PortfolioSettings {
	id: string;
	portfolioName: string;
	investmentStrategy: string;
	rebalanceStrategy: string;
	initialBalance: number;
	periodicCashflow: number;
	frequency: string;
	startDate: string;
	endDate: string;
}

export interface PortfolioTickers {
	tickerList: string[];
	allocationWeightList: string[];
	leverageFactor: string[];
	tickerCount: number;
}
