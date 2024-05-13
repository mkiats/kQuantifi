'use client';
import { useEffect, useRef } from 'react';
import { ColorType, createChart } from 'lightweight-charts';
import { newMockStockData } from '@/api/mockData';

const ChartDiagram = () => {
	const testData = {
		newMockStockData,
		colors: {
			backgroundColor: 'white',
			lineColor: '#2962FF',
			textColor: 'black',
			areaTopColor: '#2962FF',
			areaBottomColor: 'rgba(41, 98, 255, 0.28)',
		},
	};

	const chartContainerRef = useRef();

	useEffect(() => {
		const data = testData.newMockStockData;
		const handleResize = () => {
			chart.applyOptions({
				width: chartContainerRef.current.clientWidth,
			});
		};

		const chart = createChart(chartContainerRef.current, {
			layout: {
				background: {
					type: ColorType.Solid,
					color: testData.colors.backgroundColor,
				},
				textColor: testData.colors.textColor,
			},
			width: chartContainerRef.current.clientWidth,
			height: 500,
		});
		chart.timeScale().fitContent();

		const newSeries = chart.addAreaSeries({
			lineColor: testData.colors.lineColor,
			topColor: testData.colors.areaTopColor,
			bottomColor: testData.colors.areaBottomColor,
		});
		newSeries.setData(data);

		window.addEventListener('resize', handleResize);

		return () => {
			window.removeEventListener('resize', handleResize);

			chart.remove();
		};
	}, []);

	return <div ref={chartContainerRef} />;
};

export default ChartDiagram;
