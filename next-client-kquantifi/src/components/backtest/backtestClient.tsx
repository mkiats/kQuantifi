'use client';

import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { BacktestRequest } from '@/lib/types/backtest/backtestRequest';
import { getBacktestResult } from '@/lib/api/backtest';
import BacktestFormSection from './backtestForm/backtestFormSection';
import RatioSection from './ratioSection';
import SummaryDetail from './ratioDetail/summaryDetail';
import { BacktestResponse } from '@/lib/types/backtest/backtestResponse';

interface BacktestClientProps {
	children: React.ReactNode[];
}

const BacktestClient: React.FC<BacktestClientProps> = ({ children }) => {
	// UseState hooks ------------------------------
	const [backtestReq, setBacktestReq] =
		useState<BacktestRequest | null>(null);	

	// UseQuery hooks ------------------------------
	const {
		data: backtestTickerResult,
		isLoading: backtestTickerIsLoading,
		isFetched: backtestTickerIsFetched,
	} = useQuery<BacktestResponse>({
		queryKey: [
			'tickers',
			backtestReq,
		],
		queryFn: () => getBacktestResult(backtestReq!),
		enabled:
			!!backtestReq && Object.values(backtestReq).every(Boolean),
	});

	// Return ------------------------------
	return (
		<div className='flex flex-col justify-center items-center gap-8 border-2 border-green-500'>
			<BacktestFormSection
				setBacktestReq={setBacktestReq}
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
