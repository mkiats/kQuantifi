import TradingViewGraph from '@/components/ui/tradingview-graph';
import { TimeValue } from '@/lib/types/common';

interface CagrSectionProps {
	chartHeader: String;
	chartData: TimeValue[];
	ratioDescription: React.ReactNode;
	ratioDetail: React.ReactNode;
}

const CagrSection: React.FC<CagrSectionProps> = ({
	chartHeader,
	chartData,
	ratioDescription,
	ratioDetail,
}) => {
	return (
		<section className='flex w-full h-screen border-green-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				{ratioDescription}
			</div>
			<div className='w-2/3 p-8'>
				{ratioDetail}
				<TradingViewGraph displayData={chartData} rightOffset={5} />
			</div>
		</section>
	);
};

export default CagrSection;
