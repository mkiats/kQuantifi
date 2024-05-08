'use client';

import ChartDiagram from '@/components/backtest/chart/chartDiagram';
import ChartOptions from '@/components/backtest/chart/chartOptions';
import MetricDashboard from '@/components/backtest/metricDashboard';
import { getBacktestResult } from '@/api/backtest';
import { BacktestFormData } from '@/types/backtest/backtestFormOutput';
import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { BenchmarkFormData } from '@/types/backtest/benchmarkFormData';
import SetupSection from '@/components/backtest/setup/setupSection';
import MetricSection from '@/components/backtest/metric/metricSection';
import DrawdownSection from '@/components/backtest/drawdown/drawdownSection';

const Backtest = () => {
	const [backtestFormData, setBacktestFormData] =
		useState<BacktestFormData | null>(null);
	const [benchmarkFormData, setBenchmarkFormData] =
		useState<BenchmarkFormData | null>(null);

	// Submit handlers
	const backtestSubmitHandler = (
		backtestFormOutput: BacktestFormData,
	): void => {
		console.log(backtestFormOutput);
		setBacktestFormData(backtestFormOutput);
	};
	const benchmarkSubmitHandler = (
		benchmarkFormData: BenchmarkFormData,
	): void => {
		console.log(benchmarkFormData);
		setBenchmarkFormData(benchmarkFormData);
	};

	// UseQuery hooks
	const { data, isLoading, isFetched } = useQuery({
		queryKey: [
			'tickers',
			backtestFormData?.tickerName,
			backtestFormData?.timeframe,
			backtestFormData?.startDate,
			backtestFormData?.endDate,
			backtestFormData?.leverageFactor,
		],
		queryFn: () => getBacktestResult(backtestFormData),
		enabled:
			!!backtestFormData &&
			Object.values(backtestFormData).every(Boolean),
	});

	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<SetupSection
				backtestSubmitHandler={backtestSubmitHandler}
				benchmarkSubmitHandler={benchmarkSubmitHandler}
			/>
			<MetricSection />
			<DrawdownSection />
			{isFetched && (
				<div>Response is received: {JSON.stringify(data)}</div>
			)}
		</div>
	);
};

export default Backtest;
