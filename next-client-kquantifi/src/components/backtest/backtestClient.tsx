'use client';

import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { BacktestRequest } from '@/lib/types/backtest/backtestRequest';
import { getBacktestResult } from '@/lib/api/backtest';
import BacktestFormSection from './s0_backtestForm/backtestFormSection';
import { BacktestResponse } from '@/lib/types/backtest/backtestResponse';
import PerformanceSection from './s1_performance/PerformanceSection';

interface BacktestClientProps {
	children: React.ReactNode[];
}

const BacktestClient: React.FC<BacktestClientProps> = ({ children }) => {
	// UseState hooks ------------------------------
	const [backtestReq, setBacktestReq] = useState<BacktestRequest | null>(
		null,
	);

	// UseQuery hooks ------------------------------
	const {
		data: backtestTickerResult,
		isLoading: backtestTickerIsLoading,
		isFetched: backtestTickerIsFetched,
	} = useQuery<BacktestResponse>({
		queryKey: ['tickers', backtestReq],
		queryFn: () => getBacktestResult(backtestReq!),
		enabled: !!backtestReq && Object.values(backtestReq).every(Boolean),
	});

	// Return ------------------------------
	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<BacktestFormSection
				setBacktestReq={setBacktestReq}
				children={children[0]}
			/>
			{backtestTickerIsFetched && (
				<>
					<PerformanceSection
						chartHeader={'Performance'}
						chartData={
							backtestTickerResult!.investmentOutput.chartData
						}
						ratioDescription={children[1]}
					></PerformanceSection>
				</>
			)}
		</div>
	);
};

export default BacktestClient;
