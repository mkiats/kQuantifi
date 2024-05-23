import 'server-only';

import BacktestClient from '@/components/backtest/backtestClient';
import MetricSummary from '@/components/backtest/metricSummary';

const Backtest = () => {
	return (
		<div>
			<BacktestClient>
				{/* Initialise server components and pass to BacktestClient */}
				<MetricSummary metricHeader={"Summary"}/>
				<MetricSummary metricHeader={"CAGR"}/>
				<MetricSummary metricHeader={"MAX DRAWDOWN"}/>
			</BacktestClient>
		</div>
	);
};

export default Backtest;
