'use client';
import { useEffect, useRef } from 'react';
import { AreaData, ColorType, createChart, LineData } from 'lightweight-charts';
import { hslToRgbString } from '@/lib/utils/utils';
import {
	TimeValue,
} from '@/lib/types/backtest/timeValueSeries';

interface TradingViewGraphProps {
	rightOffset: number;
	displayData: TimeValue[];
}

const TradingViewGraph: React.FC<TradingViewGraphProps> = ({
	rightOffset,
	displayData,
}) => {
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
			crosshair: {
				horzLine: {
					visible: false,
					labelVisible: false,
				},
				vertLine: {
					labelVisible: false,
				},
			},
			grid: {
				vertLines: {
					visible: false,
				},
				horzLines: {
					visible: false,
				},
			},
			handleScroll: {
				mouseWheel: false,
				pressedMouseMove: false,
				horzTouchDrag: false,
				vertTouchDrag: false,
			},
			handleScale: {
				mouseWheel: false,
				pinch: false,
				axisPressedMouseMove: false,
			},
			timeScale: {
				rightOffset: 5,
			},
		});
		chart.timeScale().fitContent();

		const newSeries = chart.addAreaSeries({
			topColor: '#2962FF',
			bottomColor: 'rgba(41, 98, 255, 0.28)',
			lineColor: '#2962FF',
			lineWidth: 2,
			crosshairMarkerVisible: false,
		});

		const seriesData: AreaData[] =
			displayData.map(
				(element: TimeValue): AreaData => {
					return {
						time: element.time,
						value: element.value,
					};
				},
			);
		newSeries.setData(seriesData);

		window.addEventListener('resize', handleResize);

		return () => {
			window.removeEventListener('resize', handleResize);
			chart.remove();
		};
	}, []);

	return <div ref={chartContainerRef} />;
};

export default TradingViewGraph;
