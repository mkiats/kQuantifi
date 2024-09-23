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
import {
	getCurrentUnixTimestamp,
	formatDateString,
} from '@/lib/utils/dateUtils';

function mapToPortfolioTickers(data: TickersFormData): PortfolioTickers {
	return {
		tickerList: data.tickers.map((item) => item.ticker),
		allocationWeightList: data.tickers.map((item) => item.allocationWeight),
		leverageFactor: data.tickers.map((item) => item.leverageFactor),
		tickerCount: data.tickers.length,
	};
}

interface BacktestFormSectionProps {
	children: React.ReactNode;
	setBacktestReq: (backtestRequest: BacktestRequest) => void;
}

const BacktestFormSection: React.FC<BacktestFormSectionProps> = ({
	children,
	setBacktestReq,
}) => {
	// Submit handlers ------------------------------
	const submitHandler = (
		settingFormData: SettingsFormData,
		tickersFormData: TickersFormData,
	): void => {
		// Maps FormDataSchema to BacktestRequest
		const tempSettings: PortfolioSettings = {
			id: getCurrentUnixTimestamp(),
			portfolioName: settingFormData.portfolioName,
			investmentStrategy: settingFormData.investmentStrategy,
			rebalanceStrategy: settingFormData.rebalanceStrategy,
			initialBalance: settingFormData.initialBalance,
			periodicCashflow: settingFormData.periodicCashflow,
			frequency: settingFormData.frequency,
			startDate: formatDateString(settingFormData.startDate.toString()),
			endDate: formatDateString(settingFormData.endDate.toString()),
		};
		const tempTickers: PortfolioTickers =
			mapToPortfolioTickers(tickersFormData);

		const tempPortfolio: Portfolio = {
			portfolioSettings: tempSettings,
			portfolioTickers: tempTickers,
		};
		const tempBacktestRequest: BacktestRequest = {
			portfolio: tempPortfolio,
		};
		console.log(tempBacktestRequest);
		setBacktestReq(tempBacktestRequest);
	};
	return (
		<section className='flex w-full h-[calc(70vh)] border-red-200 border-2'>
			<div className='w-1/3 flex justify-center items-center p-8'>
				{children}
			</div>
			<div className='w-2/3 flex-col p-8 '>
				<BacktestForm submitHandler={submitHandler} />
			</div>
		</section>
	);
};

export default BacktestFormSection;
