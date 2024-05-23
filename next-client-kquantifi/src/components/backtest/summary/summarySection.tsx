import { mockStockData } from '@/lib/constants/mockData';
import TradingViewGraph from '@/components/ui/tradingview-graph';
import { TimeValue } from '@/lib/types/backtest/timeValueSeries';

interface SummarySectionProps {
	chartData: TimeValue[];
}

const SummarySection: React.FC<SummarySectionProps> = ({ chartData }) => {
	return (
		<div className='flex w-full h-[calc(100vh)] border-green-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				Metric Section
			</div>
			<div className='w-2/3 p-8'>
				<TradingViewGraph displayData={chartData} rightOffset={5} />
			</div>
		</div>
	);
};

export default SummarySection;