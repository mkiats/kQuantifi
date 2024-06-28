'client only';
const Dashboard = () => {
	return (
		<div className='flex flex-col p-8 gap-8'>
			<div className='h-[calc(30vh)] w-[calc(90wh)] rounded-xl border-2 border-blue-500 p-8'>
				Metrics of tickets: Display top 3 issues in pending calls,
				average time to handle call, number of agents online, number of
				pending calls
			</div>
			<div className='flex gap-8'>
				<div className='w-1/3 h-[calc(60vh)] flex flex-col overflow-scroll rounded-xl border-2 border-blue-500 p-8 gap-4'>
					Pending calls
					<CallCard />
					<CallCard />
					<CallCard />
					<CallCard />
					<CallCard />
					<CallCard />
					<CallCard />
					<CallCard />
					End of calls
				</div>
				<div className='w-2/3 h-[calc(60vh)] flex flex-col overflow-scroll rounded-xl border-2 border-blue-500 p-8 gap-4'>
					Agent status and call assignment
					<AgentCard />
					<AgentCard />
					<AgentCard />
					<AgentCard />
					<AgentCard />
					End of agents
				</div>
			</div>
		</div>
	);
};

export default Dashboard;

export const CallCard = ({
	customerName = 'Customer name',
	duration = 20,
	waitingTime = 10,
	showWaitingTime = true,
}) => {
	return (
		<div className='w-60 h-48 rounded border-2 border-red-600 p-2'>
			<div className='px-6 py-4'>
				<div className='font-bold text-xl text-stone-100 mb-2'>
					{customerName}
				</div>
				<p className='text-stone-100 text-md'>
					Predicted Duration: {duration}
				</p>
				{showWaitingTime && (
					<p className='text-stone-100 text-md'>
						Waiting Time: {waitingTime}
					</p>
				)}
			</div>
		</div>
	);
};

export const AgentCard = ({
	agentName = 'Agent name',
	agentExperience = 'Experienced',
}) => {
	return (
		<div className='w-full h-96 flex flex-col rounded border-2 border-yellow-100 p-4'>
			<div className='px-6 py-4'>
				<div className='font-bold text-xl text-stone-100 mb-2'>
					Name: {agentName}
				</div>
				<div className='text-md text-stone-100 mb-2'>
					Agent Experience: {agentExperience}
				</div>
			</div>
			<div className='h-60 w-full flex gap-4 overflow-scroll border-2 border-blue-500 items-center p-4'>
				<CallCard />
				<CallCard />
				<CallCard />
			</div>
		</div>
	);
};
