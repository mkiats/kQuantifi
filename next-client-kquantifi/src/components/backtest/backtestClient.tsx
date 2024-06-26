'use client';

import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { BacktestFormData } from '@/components/backtest/backtestForm/backtestForm';
import { BacktestRequest } from '@/lib/types/backtest/backtestRequest';
import { getBacktestResult } from '@/lib/api/backtest';
import BacktestFormSection from './backtestForm/backtestFormSection';
import RatioSection from './ratioSection';
import SummaryDetail from './ratioDetail/summaryDetail';

interface BacktestClientProps {
	children: React.ReactNode[];
}

const BacktestClient: React.FC<BacktestClientProps> = ({ children }) => {
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
			backtestTicker?.frequency,
			backtestTicker?.startDate,
			backtestTicker?.endDate,
			backtestTicker?.leverageFactor,
			backtestTicker?.desiredStrategy,
		],
		queryFn: () => getBacktestResult(benchmarkTicker!),
		enabled:
			!!benchmarkTicker && Object.values(benchmarkTicker).every(Boolean),
	});

	// Return ------------------------------
	return (
		<div className='flex flex-col justify-center items-center gap-8 border-2 border-green-500'>
			<BacktestFormSection
				submitHandler={submitHandler}
				children={children[0]}
			/>
			{backtestTickerIsFetched && (
				<>
					<RatioSection
						chartHeader={'SUMMARY'}
						chartData={
							backtestTickerResult!.investmentOutput.chartData
						}
						ratioDescription={children[1]}
						ratioDetail={<SummaryDetail />}
					/>
					<RatioSection
						chartHeader={'CAGR'}
						chartData={
							backtestTickerResult!.financialRatioOutput.cagr
								.chartData
						}
						ratioDescription={children[2]}
						ratioDetail={<SummaryDetail />}
					/>
					<RatioSection
						chartHeader={'MAX DRAWDOWN'}
						chartData={
							backtestTickerResult!.financialRatioOutput
								.maxDrawdown.chartData
						}
						ratioDescription={children[3]}
						ratioDetail={<SummaryDetail />}
					/>
				</>
			)}
		</div>
	);
};

export default BacktestClient;
