import { BacktestRequest } from "../types/backtest/backtestRequest";
import { BacktestResponse } from "../types/backtest/backtestResponse";

export const getBacktestResult = async (
	backtestTicker: BacktestRequest,
): Promise<BacktestResponse> => {
	const response = await fetch('http://localhost:8080/backtests', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(backtestTicker),
	});
	if (!response.ok) {
		throw new Error('Network response was not ok');
	}
	return response.json();
};


