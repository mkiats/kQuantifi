import { TimeValue, RebalanceValue } from '../common';

export interface BacktestResponse {
	investmentOutput: InvestmentOutput;
	financialRatioOutput: FinancialRatioOutput;
}

interface InvestmentOutput {
	chartData: TimeValue[];
	chartSize: number;
	assetData: Record<string, TimeValue[]>;
	rebalanceData: Record<string, RebalanceValue[]>;
}

interface CagrOutput {
	chartData: TimeValue[];
	chartSize: number;
	bestCagr?: number;
	worstCagr?: number;
}

interface MaxDrawdownOutput {
	chartData: TimeValue[];
	chartSize: number;
	startDateOfWorstDrawdownIndex: string;
	endDateOfWorstDrawdownIndex: string;
	worstDrawdownValue?: number; // Optional since Double can be null in Java
}

interface FinancialRatioOutput {
	cagr: CagrOutput;
	maxDrawdown: MaxDrawdownOutput;
	sharpeRatio: number;
	sortinoRatio: number;
	standardDeviation: number;
}
