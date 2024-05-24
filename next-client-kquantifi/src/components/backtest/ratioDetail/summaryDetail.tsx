const SummaryDetail = () => {
	return (
		<>
			<div className='grid grid-cols-7 grid-row-3 w-full h-1/2 grid-flow-row'>
				{/* Headers */}
				<>
					<div className='col-span-3 pt-2'>Metric</div>
					<div className='col-span-2'>Ticker</div>
					<div className='col-span-2'>Benchmark</div>
				</>
				{/* Row1 */}
				<>
					<div className='col-span-3'>CAGR</div>
					<div className='col-span-2'>XXX%</div>
					<div className='col-span-2'>XXX%</div>
				</>
				{/* Row2 */}
				<>
					<div className='col-span-3'>MaxDrawdown</div>
					<div className='col-span-2'>XXX%</div>
					<div className='col-span-2'>XXX%</div>
				</>
			</div>
		</>
	);
};

export default SummaryDetail;
