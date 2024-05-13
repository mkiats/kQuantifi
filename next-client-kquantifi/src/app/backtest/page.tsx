'use client';

import { getBacktestResult } from '@/api/backtest';
import { BacktestFormData } from '@/types/backtest/backtestFormOutput';
import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import SetupSection from '@/components/backtest/setup/setupSection';
import MetricSection from '@/components/backtest/metric/metricSection';
import DrawdownSection from '@/components/backtest/drawdown/drawdownSection';
import { BacktestTicker } from '@/types/backtest/backtestTicker';

const Backtest = () => {
	// UseState hooks ------------------------------
	const [backtestTicker, setBacktestTicker] = useState<BacktestTicker | null>(
		null,
	);
	const [benchmarkTicker, setBenchmarkTicker] =
		useState<BacktestTicker | null>(null);

	// Submit handlers ------------------------------
	const submitHandler = (backtestFormOutput: BacktestFormData): void => {
		console.log(backtestFormOutput);
		const newTicker: BacktestTicker = {
			tickerName: backtestFormOutput.tickerName,
			periodicAmount: backtestFormOutput.periodicAmount,
			leverageFactor: backtestFormOutput.leverageFactor,
			frequency: backtestFormOutput.frequency,
			startDate: backtestFormOutput.startDate,
			endDate: backtestFormOutput.endDate,
			desiredStrategy: backtestFormOutput.desiredStrategy,
		};
		setBacktestTicker(newTicker);
		const newBenchmarkTicker: BacktestTicker = {
			tickerName: backtestFormOutput.benchmark,
			periodicAmount: backtestFormOutput.periodicAmount,
			leverageFactor: backtestFormOutput.leverageFactor,
			frequency: backtestFormOutput.frequency,
			startDate: backtestFormOutput.startDate,
			endDate: backtestFormOutput.endDate,
			desiredStrategy: backtestFormOutput.desiredStrategy,
		};
		setBenchmarkTicker(newBenchmarkTicker);
	};

	// UseQuery hooks ------------------------------
	const {
		data: backtestTickerResult,
		isLoading: backtestTickerIsLoading,
		isFetched: backtestTickerIsFetched,
	} = useQuery({
		queryKey: [
			'tickers',
			backtestTicker?.tickerName,
			backtestTicker?.frequency,
			backtestTicker?.startDate,
			backtestTicker?.endDate,
			backtestTicker?.leverageFactor,
		],
		queryFn: () => getBacktestResult(backtestTicker),
		enabled:
			!!backtestTicker && Object.values(backtestTicker).every(Boolean),
	});

	const {
		data: benchmarkTickerResult,
		isLoading: benchmarkTickerIsLoading,
		isFetched: benchmarkTickerIsFetched,
	} = useQuery({
		queryKey: [
			'tickers',
			benchmarkTicker?.tickerName,
			benchmarkTicker?.frequency,
			benchmarkTicker?.startDate,
			benchmarkTicker?.endDate,
			benchmarkTicker?.leverageFactor,
		],
		queryFn: () => getBacktestResult(benchmarkTicker),
		enabled:
			!!benchmarkTicker && Object.values(benchmarkTicker).every(Boolean),
	});

	// Return ------------------------------
	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<SetupSection backtestSubmitHandler={submitHandler} />
			{backtestTickerIsFetched && (
				<div>
					Response is received: {JSON.stringify(backtestTickerResult)}
				</div>
			)}
			<MetricSection />
			<DrawdownSection />
		</div>
	);
};

export default Backtest;
