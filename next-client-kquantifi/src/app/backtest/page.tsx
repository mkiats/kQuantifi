import 'server-only';

import BacktestClient from '@/components/backtest/backtestClient';
import { CagrDescription, DrawdownDescription, RatioDescription, SummaryDescription } from '@/components/backtest/ratioDescription';

const Backtest = () => {
	return (
		<div>
			<BacktestClient>
				{/* Initialise server components and pass to BacktestClient */}
				<RatioDescription metricHeader={'BACKTEST FORM'} />
				<SummaryDescription metricHeader={'Summary'} />
				<CagrDescription metricHeader={'CAGR'} />
				<DrawdownDescription metricHeader={'MAX DRAWDOWN'} />
			</BacktestClient>
		</div>
	);
};

export default Backtest;
