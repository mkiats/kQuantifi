export interface TimeValueSeries {
    displayData: TimeValue[]
}

interface TimeValue {
    time: number,
    value: number,
}