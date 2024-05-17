import { BacktestTicker } from '@/lib/types/backtest/backtestTicker';

export const getBacktestResult = async (
	backtestTicker: BacktestTicker,
): Promise<String> => {
	const response = await fetch('http://localhost:8080/backtests', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(backtestTicker),
	});
	if (!response.ok) {
		// Throwing an error in an async function causes the returned Promise to be rejected
		throw new Error('Network response was not ok');
	}
	// When you return something from an async function, that value is wrapped in a Promise
	return response.json();
};


