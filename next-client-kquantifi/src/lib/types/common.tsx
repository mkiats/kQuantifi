export interface TimeValue {
    time: string;
    value: number;
    quantity: number;
}

export interface RebalanceValue {
    tickerName: string;
    beforeValue: number;
    afterValue: number;
}