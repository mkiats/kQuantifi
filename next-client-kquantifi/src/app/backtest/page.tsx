import 'server-only';

import BacktestClient from '@/components/backtest/backtestClient';
import RatioDescription from '@/components/backtest/ratioDescription';

const Backtest = () => {
	return (
		<div>
			<BacktestClient>
				{/* Initialise server components and pass to BacktestClient */}
				<RatioDescription metricHeader={"BACKTEST FORM"}/>
				<RatioDescription metricHeader={"Summary"}/>
				<RatioDescription metricHeader={"CAGR"}/>
				<RatioDescription metricHeader={"MAX DRAWDOWN"}/>
			</BacktestClient>
		</div>
	);
};

export default Backtest;
