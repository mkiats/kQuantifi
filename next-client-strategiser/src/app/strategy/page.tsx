'use client';

import ChartDiagram from '@/components/strategy/chart/chartDiagram';
import ChartOptions from '@/components/strategy/chart/chartOptions';
import MetricDashboard from '@/components/strategy/metricDashboard';
import TickerForm from '@/components/strategy/tickerForm';
import { getTicker } from '@/api/ticker';
import { TickerFormOutput } from '@/types/tickerFormOutput';
import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { log } from 'console';

const StrategyPage = () => {
	const [formData, setFormData] = useState<TickerFormOutput | null>(null);
	const [tickerName, setTickerName] = useState<string | null>(null);
	const [timeframe, setTimeframe] = useState<string | null>(null);
	const handleSubmit = (data: TickerFormOutput): void => {
		console.log(data);
		setFormData(data);
		setTickerName(data.tickerName);
		setTimeframe(data.timeframe);
	};
	const { data, isLoading, isFetched } = useQuery({
		queryKey: ['ticker', tickerName, timeframe],
		queryFn: () => getTicker(formData),
		enabled: !!tickerName && !!timeframe && !!formData,
	});
	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<section className=''>
				<TickerForm handleSubmit={handleSubmit} />
			</section>
			<section className=''>
				<ChartOptions />
			</section>
			<section className='justify-center items-center w-3/4 '>
				<ChartDiagram />
			</section>
			<section className=''>
				<MetricDashboard />
			</section>
			{isFetched && (
				<div>Response is received: {JSON.stringify(data)}</div>
			)}
		</div>
	);
};

export default StrategyPage;
