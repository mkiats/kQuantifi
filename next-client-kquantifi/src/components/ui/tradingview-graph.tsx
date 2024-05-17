'use client';
import { useEffect, useRef } from 'react';
import { ColorType, createChart, LineData } from 'lightweight-charts';
import { hslToRgbString } from '@/lib/utils';
import { TimeValueSeries } from '@/lib/types/backtest/timeValueSeries';

const TradingViewGraph: React.FC<TimeValueSeries> = ({ displayData }) => {
	const colors: {
		backgroundColor: string;
		lineColor: string;
		textColor: string;
	} = {
		backgroundColor: hslToRgbString(224, 71.4, 4.1),
		lineColor: hslToRgbString(210, 20, 100),
		textColor: hslToRgbString(210, 20, 100),
	};

	const chartContainerRef = useRef<HTMLDivElement>(null);

	useEffect(() => {
		const handleResize = () => {
			chart.applyOptions({
				width: chartContainerRef.current!.clientWidth,
			});
		};

		const chart = createChart(chartContainerRef.current!, {
			layout: {
				background: {
					type: ColorType.Solid,
					color: colors.backgroundColor,
				},
				textColor: colors.textColor,
			},
			width: chartContainerRef.current!.clientWidth,
			height: 500,
		});
		chart.timeScale().fitContent();

		const newSeries = chart.addLineSeries({
			color: colors.lineColor,
		});

		const lineData: LineData[] = displayData.map((item) => ({
			time: Math.floor(
				new Date(item.time).getTime() / 1000,
			) as LineData['time'], // Convert to Unix timestamp in seconds
			value: item.value,
		}));

		newSeries.setData(lineData);

		window.addEventListener('resize', handleResize);

		return () => {
			window.removeEventListener('resize', handleResize);
			chart.remove();
		};
	}, []);

	return <div ref={chartContainerRef} />;
};

export default TradingViewGraph;
