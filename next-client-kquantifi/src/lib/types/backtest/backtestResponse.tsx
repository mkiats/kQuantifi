export interface BacktestResponse {
    investmentOutput: InvestmentOutput;
    financialRatioOutput: FinancialRatioOutput;
}

interface InvestmentOutput {
    stockTimestamp: string[];
    stockAdjustedValue: number[];
    stockAdjustedQuantity: number[];
    investedAmount: number;
    periodicAmount: number;
    timeframe: string;
}

interface CAGR {
    timestampArr: string[];
    cagrArr: number[];
    bestCagr: number;
    worstCagr: number;
    cagr: number;
}

interface MaxDrawdown {
    timestampArr: string[];
    drawdownArr: number[];
    startDateOfWorstDrawdownIndex: number;
    endDateOfWorstDrawdownIndex: number;
    worstDrawdownValue: number;
}

interface FinancialRatioOutput {
    cagrOutput: CAGR;
    maxDrawdown: MaxDrawdown;
    sharpeRatio: number;
    sortinoRatio: number;
    standardDeviation: number;
}

