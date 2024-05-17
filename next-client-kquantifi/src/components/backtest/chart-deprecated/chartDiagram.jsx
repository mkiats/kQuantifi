'use client';
import { useEffect, useRef } from 'react';
import { ColorType, createChart } from 'lightweight-charts';
import { newMockStockData } from '@/lib/constants/mockData';
import { hslToRgb, hslToRgbString } from '@/lib/utils';

const ChartDiagram = () => {
	const testData = {
		newMockStockData,
		colors: {
			backgroundColor: hslToRgbString(224, 71.4, 4.1),
			lineColor: hslToRgbString(210, 20, 100),
			textColor: hslToRgbString(210, 20, 100),
		},
	};

	const chartContainerRef = useRef(null);

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

		const newSeries = chart.addLineSeries({
			color: testData.colors.lineColor,
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
