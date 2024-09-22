export interface BacktestRequest {
	portfolio: Portfolio;
}

interface Portfolio {
	portfolioSettings: PortfolioSettings;
	portfolioTickers: PortfolioTickers;
}

interface PortfolioSettings {
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

interface PortfolioTickers {
	tickerList: string[];
	allocationWeightList: string[];
	leverageFactor: string[];
	tickerCount: number;
}
