export interface BacktestTicker {
    tickerName: string,
    periodicAmount: number,
    leverageFactor: number,
    frequency: string,
    startDate: string,
    endDate: string,
    desiredStrategy: string,
}