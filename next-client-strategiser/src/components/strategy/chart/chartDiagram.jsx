'use client';
import { useEffect, useRef } from 'react';
import { createChart } from 'lightweight-charts';
import { newMockStockData } from '@/api/mockData';

const ChartDiagram = () => {
	const chartRef = useRef(null);

	useEffect(() => {
		const chartOptions = {
			layout: {
				textColor: 'white',
				background: { color: 'transparent' },
			},
		};
		const chart = createChart(chartRef.current, chartOptions);
		const areaSeries = chart.addAreaSeries({
			topColor: 'rgba(38, 198, 218, 0.56)',
			bottomColor: 'rgba(38, 198, 218, 0.04)',
			lineColor: 'rgba(38, 198, 218, 1)',
			lineWidth: 2,
			crossHairMarkerVisible: false,
		});
		areaSeries.setData(newMockStockData);

		chart.timeScale().fitContent();

		return () => {
			if (chart) {
				chart.remove();
			}
		};
	}, []);

	return (
		<div
			className='w-[calc(50vw)] h-[calc(50vh)] p-16'
			ref={chartRef}
		></div>
	);
};

export default ChartDiagram;
