import { BacktestRequest } from '../types/backtest/backtestRequest';
import { BacktestResponse } from '../types/backtest/backtestResponse';

export const getBacktestResult = (
	backtestTicker: BacktestRequest,
): Promise<BacktestResponse> => {
	return fetch('http://localhost:8080/backtests', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(backtestTicker),
	}).then((response) => {
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		return response.json();
	});
};
