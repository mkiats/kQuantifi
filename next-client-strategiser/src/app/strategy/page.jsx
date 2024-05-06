import ChartDiagram from '@/components/strategy/chart/chartDiagram';
import ChartOptions from '@/components/strategy/chart/chartOptions';
import MetricDashboard from '@/components/strategy/metricDashboard';
import TickerForm from '@/components/strategy/tickerForm';
import { newMockStockData } from '@/api/mockData';

const StrategyPage = () => {
	return (
		<div className='flex flex-col justify-center items-center gap-8'>
			<section className=''>
				<TickerForm />
			</section>
			<section className=''>
				<ChartOptions />
			</section>
			<section className='justify-center items-center w-3/4 '>
				<ChartDiagram className='' />
			</section>
			<section className=''>
				<MetricDashboard />
			</section>
		</div>
	);
};

export default StrategyPage;
