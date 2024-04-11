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
			width: 600,
			height: 300,
		};
		const chart = createChart(chartRef.current, chartOptions);
		const lineSeries = chart.addLineSeries({ color: 'white' });
		lineSeries.setData(newMockStockData);

		chart.timeScale().fitContent();

		return () => {
			if (chart) {
				chart.remove();
			}
		};
	}, []);

	return (
		<div className='h-96' ref={chartRef}>
			
		</div>
	);
};

export default ChartDiagram;
