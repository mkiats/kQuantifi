import { newMockStockData } from '@/lib/constants/mockData';

const MetricSection = () => {
	const displayData = newMockStockData;

	return (
		<div className='flex w-full h-[calc(100vh)] border-green-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				LeftSide
			</div>
			<div className='w-2/3 p-8'>
				<Chart />
			</div>
		</div>
	);
};

export default MetricSection;
