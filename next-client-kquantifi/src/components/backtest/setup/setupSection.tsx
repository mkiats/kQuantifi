"use client"

import BacktestForm from './backtestForm';
import BenchmarkForm from './benchmarkForm';

const SetupSection = ({ backtestSubmitHandler, benchmarkSubmitHandler }) => {
	return (
		<div className='flex w-full h-[calc(100vh)] border-red-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>LeftSide</div>
			<div className='w-2/3 p-8'>
				<BacktestForm handleSubmit={backtestSubmitHandler} />
				<BenchmarkForm handleSubmit={benchmarkSubmitHandler} />
			</div>
		</div>
	);
};

export default SetupSection;
