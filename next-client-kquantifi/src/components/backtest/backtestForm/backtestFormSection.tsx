'use client';

import React from 'react';
import { BacktestFormData } from './backtestForm';
import BacktestForm from './backtestForm';

interface BacktestFormSectionProps {
	children: React.ReactNode;
	submitHandler: (backtestFormData: BacktestFormData) => void;
}

const BacktestFormSection: React.FC<BacktestFormSectionProps> = ({
	children,
	submitHandler,
}) => {
	return (
		<div className='flex w-full h-[calc(70vh)] border-red-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				{children}
			</div>
			<div className='w-2/3 p-8'>
				<BacktestForm submitHandler={submitHandler} />
			</div>
		</div>
	);
};

export default BacktestFormSection;
