import TradingViewGraph from '@/components/ui/tradingview-graph';
import { TimeValue } from '@/lib/types/backtest/timeValueSeries';

interface MetricDetailProps {
	chartHeader: String;
	chartData: TimeValue[];
    children: React.ReactNode;
}

const MetricDetail: React.FC<MetricDetailProps> = ({ chartHeader, chartData, children }) => {
	return (
		<div className='flex w-full h-[calc(100vh)] border-green-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				{children}
			</div>
			<div className='w-2/3 p-8'>
				{chartHeader}
				<TradingViewGraph displayData={chartData} rightOffset={5} />
			</div>
		</div>
	);
};

export default MetricDetail;