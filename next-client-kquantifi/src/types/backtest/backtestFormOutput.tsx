export interface BacktestFormData {
    tickerName: string,
    dcaAmount: number,
    leverageFactor: number,
    timeframe: string,
    startDate: string,
    endDate: string,
    benchmark: string,
    benchmarkGrowthRate: number,
}