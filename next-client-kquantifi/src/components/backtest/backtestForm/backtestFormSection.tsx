'use client';

import React from 'react';
import { SettingsFormData, TickersFormData } from './backtestForm';
import {
	BacktestRequest,
	PortfolioSettings,
	PortfolioTickers,
	Portfolio,
} from '@/lib/types/backtest/backtestRequest';

import BacktestForm from './backtestForm';
import { getCurrentUnixTimestamp } from '@/lib/utils/dateUtils';

interface BacktestFormSectionProps {
	children: React.ReactNode;
	setBacktestReq: (backtestRequest: BacktestRequest) => void;
}

const BacktestFormSection: React.FC<BacktestFormSectionProps> = ({
	children,
	setBacktestReq,
}) => {
	// Submit handlers ------------------------------
	const submitHandler = (settingFormData: SettingsFormData, tickersFormData: TickersFormData): void => {
		// Maps FormDataSchema to BacktestRequest
		const tempSettings: PortfolioSettings = {
			id: getCurrentUnixTimestamp(),
			portfolioName: settingFormData.frequency,
			investmentStrategy: settingFormData.frequency,
			rebalanceStrategy: settingFormData.frequency,
			initialBalance: settingFormData.periodicCashflow,
			periodicCashflow: settingFormData.periodicCashflow,
			frequency: settingFormData.frequency,
			startDate: settingFormData.startDate.toString(),
			endDate: settingFormData.endDate.toString(),
		};
		const tempTickers: PortfolioTickers = {
			tickerList: [],
			allocationWeightList: [],
			leverageFactor: [],
			tickerCount: 0,
		};
		const tempPortfolio: Portfolio = {
			portfolioSettings: tempSettings,
			portfolioTickers: tempTickers,
		};
		const tempBacktestRequest: BacktestRequest = {
			portfolio: tempPortfolio,
		};
		setBacktestReq(tempBacktestRequest);

	};
	return (
		<section className='flex w-full h-[calc(70vh)] border-red-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				{children}
			</div>
			<div className='w-2/3 p-8'>
				<BacktestForm submitHandler={submitHandler} />
			</div>
		</section>
	);
};

export default BacktestFormSection;
