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
	const submitHandler = ({settings: SettingsFormData, tickers: TickersFormData}): void => {
		const tempSettings: PortfolioSettings = {
			id: getCurrentUnixTimestamp(),
			portfolioName: settings.frequency,
			investmentStrategy: settings.frequency,
			rebalanceStrategy: settings.frequency,
			initialBalance: settings.periodicAmount,
			periodicCashflow: settings.periodicAmount,
			frequency: settings.frequency,
			startDate: settings.startDate.toString(),
			endDate: settings.endDate.toString(),
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

		// setBacktestTicker(newTicker);
		// if (backtestFormData.benchmark) {
		// 	const newBenchmarkTicker: BacktestRequest = {
		// 		tickerName: backtestFormData.benchmark,
		// 		periodicAmount: backtestFormData.periodicAmount,
		// 		leverageFactor: backtestFormData.leverageFactor,
		// 		frequency: backtestFormData.frequency,
		// 		startDate: backtestFormData.startDate.toString(),
		// 		endDate: backtestFormData.endDate.toString(),
		// 		desiredStrategy: backtestFormData.desiredStrategy,
		// 	};
		// 	setBenchmarkTicker(newBenchmarkTicker);
		// }
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
