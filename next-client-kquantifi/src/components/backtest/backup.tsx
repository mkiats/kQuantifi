// 'use client';

// import { useQuery } from '@tanstack/react-query';
// import { useState } from 'react';
// import { BacktestFormData } from '@/components/backtest/setup/backtestForm';
// import { BacktestRequest } from '@/lib/types/backtest/backtestRequest';
// import { getBacktestResult } from '@/lib/api/backtest';
// import DrawdownSection from '@/components/backtest/drawdown/drawdownSection';
// import CagrSection from '@/components/backtest/cagr/cagrSection';
// import SummarySection from '@/components/backtest/summary/summarySection';
// import BacktestFormSection from '@/components/backtest/setup/backtestFormSection';

// const BacktestClient = () => {
// 	// UseState hooks ------------------------------
// 	const [backtestTicker, setBacktestTicker] =
// 		useState<BacktestRequest | null>(null);
// 	const [benchmarkTicker, setBenchmarkTicker] =
// 		useState<BacktestRequest | null>(null);

// 	// Submit handlers ------------------------------
// 	const submitHandler = (backtestFormData: BacktestFormData): void => {
// 		console.log(backtestFormData);
// 		const newTicker: BacktestRequest = {
// 			tickerName: backtestFormData.tickerName,
// 			periodicAmount: backtestFormData.periodicAmount,
// 			leverageFactor: backtestFormData.leverageFactor,
// 			frequency: backtestFormData.frequency,
// 			startDate: backtestFormData.startDate.toString(),
// 			endDate: backtestFormData.endDate.toString(),
// 			desiredStrategy: backtestFormData.desiredStrategy,
// 		};
// 		setBacktestTicker(newTicker);
// 		if (backtestFormData.benchmark) {
// 			const newBenchmarkTicker: BacktestRequest = {
// 				tickerName: backtestFormData.benchmark,
// 				periodicAmount: backtestFormData.periodicAmount,
// 				leverageFactor: backtestFormData.leverageFactor,
// 				frequency: backtestFormData.frequency,
// 				startDate: backtestFormData.startDate.toString(),
// 				endDate: backtestFormData.endDate.toString(),
// 				desiredStrategy: backtestFormData.desiredStrategy,
// 			};
// 			setBenchmarkTicker(newBenchmarkTicker);
// 		}
// 	};

// 	// UseQuery hooks ------------------------------
// 	const {
// 		data: backtestTickerResult,
// 		isLoading: backtestTickerIsLoading,
// 		isFetched: backtestTickerIsFetched,
// 	} = useQuery({
// 		queryKey: [
// 			'tickers',
// 			backtestTicker?.tickerName,
// 			backtestTicker?.frequency,
// 			backtestTicker?.startDate,
// 			backtestTicker?.endDate,
// 			backtestTicker?.leverageFactor,
// 			backtestTicker?.desiredStrategy,
// 		],
// 		queryFn: () => getBacktestResult(backtestTicker!),
// 		enabled:
// 			!!backtestTicker && Object.values(backtestTicker).every(Boolean),
// 	});

// 	const {
// 		data: benchmarkTickerResult,
// 		isLoading: benchmarkTickerIsLoading,
// 		isFetched: benchmarkTickerIsFetched,
// 	} = useQuery({
// 		queryKey: [
// 			'tickers',
// 			benchmarkTicker?.tickerName,
// 			backtestTicker?.frequency,
// 			backtestTicker?.startDate,
// 			backtestTicker?.endDate,
// 			backtestTicker?.leverageFactor,
// 			backtestTicker?.desiredStrategy,
// 		],
// 		queryFn: () => getBacktestResult(benchmarkTicker!),
// 		enabled:
// 			!!benchmarkTicker && Object.values(benchmarkTicker).every(Boolean),
// 	});

// 	// Return ------------------------------
// 	return (
// 		<div className='flex flex-col justify-center items-center gap-8'>
// 			<BacktestFormSection submitHandler={submitHandler} />
// 			{backtestTickerIsFetched && (
// 				<>
// 					<SummarySection
// 						chartData={
// 							backtestTickerResult!.investmentOutput.chartData
// 						}
// 					/>
// 					<CagrSection
// 						chartData={
// 							backtestTickerResult!.financialRatioOutput.cagr
// 								.chartData
// 						}
// 					/>
// 					<DrawdownSection
// 						chartData={
// 							backtestTickerResult!.financialRatioOutput
// 								.maxDrawdown.chartData
// 						}
// 					/>
// 				</>
// 			)}
// 		</div>
// 	);
// };

// export default BacktestClient;
