export interface BacktestRequest {
    tickerName: string,
    periodicAmount: number,
    leverageFactor: number,
    frequency: string,
    startDate: string,
    endDate: string,
    desiredStrategy: string,
}