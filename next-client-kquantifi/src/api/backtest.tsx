import { BacktestFormData } from '@/types/backtest/backtestFormOutput';
import { BenchmarkFormData } from '@/types/backtest/benchmarkFormData';

export const getBacktestResult = async (
	backtestFormOutput: BacktestFormData,
): Promise<String> => {
	const response = await fetch('http://localhost:8080/tickers', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(backtestFormOutput),
	});
	if (!response.ok) {
		// Throwing an error in an async function causes the returned Promise to be rejected
		throw new Error('Network response was not ok');
	}
	// When you return something from an async function, that value is wrapped in a Promise
	return response.json();
};


export const getBenchmarkResult = async (
	benchmarkFormOutput: BenchmarkFormData,
): Promise<String> => {
	const response = await fetch('http://localhost:8080/tickers', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(benchmarkFormOutput),
	});
	if (!response.ok) {
		// Throwing an error in an async function causes the returned Promise to be rejected
		throw new Error('Network response was not ok');
	}
	// When you return something from an async function, that value is wrapped in a Promise
	return response.json();
};
