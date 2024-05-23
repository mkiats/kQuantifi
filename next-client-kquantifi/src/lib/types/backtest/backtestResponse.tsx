import { TimeValue } from "./timeValueSeries";

export interface BacktestResponse {
	investmentOutput: InvestmentOutput;
	financialRatioOutput: FinancialRatioOutput;
}

interface InvestmentOutput {
	stockAdjustedQuantity: number[];
	chartData: TimeValue[];
	chartSize: number;
	investedAmount: number;
	periodicAmount: number;
	timeframe: string;
}

interface CAGR {
	chartData: TimeValue[];
	chartSize: number;
	bestCagr: number;
	worstCagr: number;
	cagr: number;
}

interface MaxDrawdown {
	chartData: TimeValue[];
	chartSize: number;
	startDateOfWorstDrawdownIndex: string;
	endDateOfWorstDrawdownIndex: string;
	worstDrawdownValue: number;
}

interface FinancialRatioOutput {
	cagr: CAGR;
	maxDrawdown: MaxDrawdown;
	sharpeRatio: number;
	sortinoRatio: number;
	standardDeviation: number;
}
