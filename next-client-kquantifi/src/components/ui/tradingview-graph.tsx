'use client';
import { useEffect, useRef } from 'react';
import { AreaData, ColorType, createChart, LineData } from 'lightweight-charts';
import { hslToRgbString } from '@/lib/utils/utils';
import { TimeValue } from '@/lib/types/common';

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
		foregroundColor: string;
	} = {
		backgroundColor: hslToRgbString(0, 0, 100),
		foregroundColor: hslToRgbString(0, 0, 0),
	};

	const chartContainerRef = useRef<HTMLDivElement>(null);

	useEffect(() => {
		const handleResize = () => {
			chart.applyOptions({
				width: chartContainerRef.current!.clientWidth,
			});
			chart.timeScale().fitContent();
		};

		const chart = createChart(chartContainerRef.current!, {
			layout: {
				background: {
					type: ColorType.Solid,
					color: colors.backgroundColor,
				},
				textColor: colors.foregroundColor,
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
					visible: false,
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
				rightOffset: rightOffset,
			},
		});
		chart.timeScale().fitContent();

		const newSeries = chart.addAreaSeries({
			// topColor: `${colors.foregroundColor}`,
			// bottomColor: `${colors.foregroundColor}`,
			lineColor: `${colors.foregroundColor}`,
			lineWidth: 2,
			crosshairMarkerVisible: true,
		});

		const seriesData: AreaData[] = displayData.map(
			(element: TimeValue): AreaData => {
				return {
					time: element.time,
					value: element.value,
				};
			},
		);
		newSeries.setData(seriesData);

		window.addEventListener('resize', handleResize);

		// Tooltip setup
		const toolTipWidth = 100;
		const toolTipHeight = 100;
		const toolTipMargin = 15;

		const toolTip = document.createElement('div');

		toolTip.className = `
		w-32             /* width: 96px */
		h-20             /* height: 80px */
		absolute         /* position: absolute */
		hidden           /* display: none */
		p-2              /* padding: 8px */
		box-border       /* box-sizing: border-box */
		border-2
		text-sm          /* font-size: 10px */
		text-center      /* text-align: center */
		z-10             /* z-index: 1 */
		pointer-events-none /* pointer-events: none */
		border           /* border: 1px solid */
		rounded-sm       /* border-radius: 2px */
		bg-[${colors.foregroundColor}]  /* background color */
		text-[${colors.foregroundColor}]  /* text color */
		border-[${colors.foregroundColor}]  /* border color */
		font-sans        /* font-family: -apple-system, BlinkMacSystemFont, 'Trebuchet MS', Roboto, Ubuntu, sans-serif */
		antialiased      /* -webkit-font-smoothing: antialiased */
		subpixel-antialiased /* -moz-osx-font-smoothing: grayscale */`;

		chartContainerRef.current!.appendChild(toolTip);

		// Subscribe to crosshair move event
		chart.subscribeCrosshairMove((param) => {
			// console.log(
			// 	'X: ' + (param.point?.x ?? 0) + 'Y: ' + (param.point?.y ?? 0),
			// );
			if (
				param.point === undefined ||
				!param.time ||
				param.point.x < 0 ||
				param.point.x > chartContainerRef.current!.clientWidth ||
				param.point.y < 0 ||
				param.point.y > chartContainerRef.current!.clientHeight
			) {
				toolTip.style.display = 'none';
			} else {
				const dateStr = param.time;
				toolTip.style.display = 'block';
				const data = param.seriesData.get(newSeries);
				let price;

				if (data) {
					if ('value' in data) {
						price = data.value;
					} else if ('close' in data) {
						price = data.close;
					} else {
						price = 0;
					}
				}
				toolTip.innerHTML = `
				<div style="color: ${colors.foregroundColor}">${dateStr}</div>
				<div style="margin: 4px 0px; color: ${colors.foregroundColor}">${
					Math.round(100 * price!) / 100
				}</div>
				`;
				const coordinate = newSeries.priceToCoordinate(price!);
				let shiftedCoordinate = param.point.x - 50;
				if (coordinate === null) {
					return;
				}
				shiftedCoordinate = Math.max(
					0,
					Math.min(
						chartContainerRef.current!.clientWidth - toolTipWidth,
						shiftedCoordinate,
					),
				);
				const coordinateY =
					coordinate - toolTipHeight - toolTipMargin > 0
						? coordinate - toolTipHeight - toolTipMargin
						: Math.max(
								0,
								Math.min(
									chartContainerRef.current!.clientHeight -
										toolTipHeight -
										toolTipMargin,
									coordinate + toolTipMargin,
								),
						  );
				toolTip.style.left = shiftedCoordinate + 'px';
				toolTip.style.top = coordinateY + 'px';
			}
		});

		return () => {
			window.removeEventListener('resize', handleResize);
			chart.remove();
		};
	}, [colors, displayData]);

	return <div ref={chartContainerRef} className='relative' />;
};

export default TradingViewGraph;
