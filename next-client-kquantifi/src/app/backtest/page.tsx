'use client';

import { useQuery } from '@tanstack/react-query';
import { useEffect, useState } from 'react';
import SetupSection from '@/components/backtest/setup/setupSection';
import MetricSection from '@/components/backtest/metric/metricSection';
import DrawdownSection from '@/components/backtest/drawdown/drawdownSection';
import { BacktestFormData } from '@/components/backtest/setup/backtestForm';
import { BacktestRequest } from '@/lib/types/backtest/backtestRequest';
import { getBacktestResult } from '@/lib/api/backtest';
import { mockStockData } from '@/lib/constants/mockData';
import { TimeValue } from '@/lib/types/backtest/timeValueSeries';

const Backtest = () => {
	// UseState hooks ------------------------------
	const [backtestTicker, setBacktestTicker] =
		useState<BacktestRequest | null>(null);
	const [benchmarkTicker, setBenchmarkTicker] =
		useState<BacktestRequest | null>(null);

	// Submit handlers ------------------------------
	const submitHandler = (backtestFormData: BacktestFormData): void => {
		console.log(backtestFormData);
		const newTicker: BacktestRequest = {
			tickerName: backtestFormData.tickerName,
			periodicAmount: backtestFormData.periodicAmount,
			leverageFactor: backtestFormData.leverageFactor,
			frequency: backtestFormData.frequency,
			startDate: backtestFormData.startDate.toString(),
			endDate: backtestFormData.endDate.toString(),
			desiredStrategy: backtestFormData.desiredStrategy,
		};
		setBacktestTicker(newTicker);
		if (backtestFormData.benchmark) {
			const newBenchmarkTicker: BacktestRequest = {
				tickerName: backtestFormData.benchmark,
				periodicAmount: backtestFormData.periodicAmount,
				leverageFactor: backtestFormData.leverageFactor,
				frequency: backtestFormData.frequency,
				startDate: backtestFormData.startDate.toString(),
				endDate: backtestFormData.endDate.toString(),
				desiredStrategy: backtestFormData.desiredStrategy,
			};
			setBenchmarkTicker(newBenchmarkTicker);
		}
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
			backtestTicker?.desiredStrategy,
		],
		queryFn: () => getBacktestResult(backtestTicker!),
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
			backtestTicker?.desiredStrategy,
		],
		queryFn: () => getBacktestResult(benchmarkTicker!),
		enabled:
			!!benchmarkTicker && Object.values(benchmarkTicker).every(Boolean),
	});

	let metricTimeValue: TimeValue[] = [];
	useEffect(() => {
		if (backtestTickerResult) {
			let size =
				backtestTickerResult?.investmentOutput.stockTimestamp.length;
			for (let i = 0; i < size!; i++) {
				metricTimeValue.push({
					time: backtestTickerResult.investmentOutput.stockTimestamp[
						i
					],
					value: backtestTickerResult.investmentOutput
						.stockAdjustedValue[i],
				});
			}
		}
	}, [backtestTickerResult]);

	// Return ------------------------------
	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<SetupSection submitHandler={submitHandler} />
			{backtestTickerIsFetched && (
				<>
					<div>
						Response is received:{' '}
						{JSON.stringify(backtestTickerResult)}
					</div>
					<MetricSection chartData={metricTimeValue} />
					<DrawdownSection />
				</>
			)}
		</div>
	);
};

export default Backtest;
