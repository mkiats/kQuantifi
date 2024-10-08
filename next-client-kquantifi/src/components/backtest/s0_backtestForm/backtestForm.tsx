'use client';

import { zodResolver } from '@hookform/resolvers/zod';
import { useForm, useFieldArray } from 'react-hook-form';
import { z } from 'zod';
import { Button } from '@/components/ui/button';
import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import {
	Popover,
	PopoverTrigger,
	PopoverContent,
} from '@/components/ui/popover';
import { Calendar } from '@/components/ui/calendar';
import { Calendar as CalendarIcon } from 'lucide-react';
import { format } from 'date-fns';
import { cn } from '@/lib/utils/utils';
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue,
} from '@/components/ui/select';
import { useState } from 'react';

const frequencyEnum = ['WEEKLY', 'MONTHLY'] as const;
const investmentStrategyEnum = ['DollarCostAverage'] as const;
const rebalanceStrategyEnum = ['RelativeBands'] as const;

const settingsSchema = z.object({
	portfolioName: z.string().min(2),
	investmentStrategy: z.enum(investmentStrategyEnum, {
		required_error: 'frequency not specified',
	}),
	rebalanceStrategy: z.enum(rebalanceStrategyEnum, {
		required_error: 'frequency not specified',
	}),
	initialBalance: z
		.string()
		.min(3, 'Minimum value is 100. ')
		.max(7, 'Maximum value is 9999999. '),
	periodicCashflow: z
		.string()
		.min(3, 'Minimum value is 100. ')
		.max(7, 'Maximum value is 9999999. '),
	frequency: z.enum(frequencyEnum, {
		required_error: 'frequency not specified',
	}),
	startDate: z.coerce.date({
		required_error: 'startDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
	endDate: z.coerce.date({
		required_error: 'endDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
});

const tickersSchema = z.object({
	tickers: z
		.array(
			z.object({
				ticker: z.string().min(1, 'Ticker is required.'),
				allocationWeight: z
					.string()
					.min(1, 'Allocation weight is required.')
					.max(100, 'Allocation weight cannot exceed 100%.'),
				leverageFactor: z
					.string()
					.min(1, 'Leverage factor is required.')
					.max(4, 'Leverage factor too high.'),
			}),
		)
		.max(5, 'You can only add up to 5 tickers.'),
});

export type SettingsFormData = z.infer<typeof settingsSchema>;
export type TickersFormData = z.infer<typeof tickersSchema>;

interface BacktestFormProps {
	submitHandler: (
		settingsFormData: SettingsFormData,
		tickersFormData: TickersFormData,
	) => void;
}

const BacktestForm: React.FC<BacktestFormProps> = ({ submitHandler }) => {
	const [settings, setSettings] = useState<SettingsFormData | null>(null);
	const [tickers, setTickers] = useState<TickersFormData | null>(null);

	// Settings Form & Submit handlers ------------------------------
	const settingsForm = useForm<z.infer<typeof settingsSchema>>({
		mode: 'onSubmit',
		resolver: zodResolver(settingsSchema),
		defaultValues: {
			portfolioName: 'RagsToRiches',
			investmentStrategy: 'DollarCostAverage',
			rebalanceStrategy: 'RelativeBands',
			initialBalance: '1000',
			periodicCashflow: '100',
			frequency: 'MONTHLY',
			startDate: new Date('2024-06-01'),
			endDate: new Date(Date.now()),
		},
	});
	function settingsFormSubmitHandler(values: SettingsFormData) {
		setSettings(values);
	}

	// Tickers Form & Submit handlers ------------------------------
	const tickersForm = useForm<z.infer<typeof tickersSchema>>({
		resolver: zodResolver(tickersSchema),
		defaultValues: {
			tickers: [
				{ ticker: 'SPY', allocationWeight: '100', leverageFactor: '2' },
			],
		},
	});

	const { fields, append, remove } = useFieldArray({
		control: tickersForm.control,
		name: 'tickers',
	});

	function tickersFormSubmitHandler(values: TickersFormData) {
		setTickers(values);
		if (settings && values) {
			submitHandler(settings, values);
		}
	}

	return (
		<>
			<Form {...settingsForm}>
				<form
					onSubmit={settingsForm.handleSubmit(
						settingsFormSubmitHandler,
					)}
					className='w-full py-8 grid gap-4 justify-items-center content-start grid-rows-5 grid-cols-2 lg:grid-rows-3 lg:grid-cols-3 '
				>
					<FormField
						control={settingsForm.control}
						name='portfolioName'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Ticker Name</FormLabel>
								<FormControl>
									<Input {...field} />
								</FormControl>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='investmentStrategy'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Investment Strategy</FormLabel>
								<Select
									onValueChange={field.onChange}
									defaultValue={field.value}
								>
									<FormControl>
										<SelectTrigger className='w-[280px]'>
											<SelectValue placeholder='Default investment strategy: DollarCostAverage' />
										</SelectTrigger>
									</FormControl>
									<SelectContent>
										{investmentStrategyEnum.map(
											(investmentElem) => (
												<SelectItem
													key={investmentElem}
													value={investmentElem}
												>
													{investmentElem}
												</SelectItem>
											),
										)}
									</SelectContent>
								</Select>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='rebalanceStrategy'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Rebalance Strategy</FormLabel>
								<Select
									onValueChange={field.onChange}
									defaultValue={field.value}
								>
									<FormControl>
										<SelectTrigger className='w-[280px]'>
											<SelectValue placeholder='Default rebalance strategy: RelativeBands' />
										</SelectTrigger>
									</FormControl>
									<SelectContent>
										{rebalanceStrategyEnum.map(
											(rebalanceElem) => (
												<SelectItem
													key={rebalanceElem}
													value={rebalanceElem}
												>
													{rebalanceElem}
												</SelectItem>
											),
										)}
									</SelectContent>
								</Select>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='initialBalance'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Initial balance: </FormLabel>
								<FormControl>
									<Input type='number' {...field} />
								</FormControl>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='periodicCashflow'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Periodic inflow: </FormLabel>
								<FormControl>
									<Input type='number' {...field} />
								</FormControl>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='frequency'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Timeframe</FormLabel>
								<Select
									onValueChange={field.onChange}
									defaultValue={field.value}
								>
									<FormControl>
										<SelectTrigger className='w-[280px]'>
											<SelectValue placeholder='Default frequency: weekly' />
										</SelectTrigger>
									</FormControl>
									<SelectContent>
										{frequencyEnum.map((frequencyElem) => (
											<SelectItem
												key={frequencyElem}
												value={frequencyElem}
											>
												{frequencyElem}
											</SelectItem>
										))}
									</SelectContent>
								</Select>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='startDate'
						render={({ field }) => (
							<FormItem>
								<FormLabel>Start date</FormLabel>
								<Popover>
									<PopoverTrigger asChild>
										<FormControl>
											<Button
												variant={'outline'}
												className={cn(
													'w-[280px] justify-start text-left font-normal flex',
													!field.value &&
														'text-muted-foreground',
												)}
											>
												<CalendarIcon className='mr-2 h-4 w-4' />
												{field.value ? (
													format(field.value, 'PPP')
												) : (
													<span>Pick a date</span>
												)}
											</Button>
										</FormControl>
									</PopoverTrigger>
									<PopoverContent className='w-auto p-0'>
										<Calendar
											mode='single'
											selected={field.value}
											onSelect={field.onChange}
											initialFocus
										/>
									</PopoverContent>
								</Popover>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={settingsForm.control}
						name='endDate'
						render={({ field }) => (
							<FormItem>
								<FormLabel>End date</FormLabel>
								<Popover>
									<PopoverTrigger asChild>
										<FormControl>
											<Button
												variant={'outline'}
												className={cn(
													'w-[280px] justify-start text-left font-normal flex',
													!field.value &&
														'text-muted-foreground',
												)}
											>
												<CalendarIcon className='mr-2 h-4 w-4' />
												{field.value ? (
													<>
														{format(
															field.value,
															'PPP',
														)}
													</>
												) : (
													<span>Pick a date</span>
												)}
											</Button>
										</FormControl>
									</PopoverTrigger>
									<PopoverContent className='w-auto p-0'>
										<Calendar
											mode='single'
											selected={field.value}
											onSelect={field.onChange}
											initialFocus
										/>
									</PopoverContent>
								</Popover>
								<FormDescription></FormDescription>
								<FormMessage />
							</FormItem>
						)}
					/>
					<Button className='self-center' type='submit'>
						Submit
					</Button>
				</form>
			</Form>

			<Form {...tickersForm}>
				<form
					onSubmit={tickersForm.handleSubmit(
						tickersFormSubmitHandler,
					)}
					className='w-full flex-col space-y-4 py-8'
				>
					{fields.map((field, index) => (
						<div key={field.id} className='flex space-x-4'>
							<FormField
								control={tickersForm.control}
								name={`tickers.${index}.ticker`}
								render={({ field }) => (
									<FormItem>
										<FormLabel>Ticker</FormLabel>
										<FormControl>
											<Input
												placeholder='Ticker'
												{...field}
											/>
										</FormControl>
										<FormMessage />
									</FormItem>
								)}
							/>
							<FormField
								control={tickersForm.control}
								name={`tickers.${index}.allocationWeight`}
								render={({ field }) => (
									<FormItem>
										<FormLabel>Allocation Weight</FormLabel>
										<FormControl>
											<Input
												placeholder='Weight (%)'
												{...field}
											/>
										</FormControl>
										<FormMessage />
									</FormItem>
								)}
							/>
							<FormField
								control={tickersForm.control}
								name={`tickers.${index}.leverageFactor`}
								render={({ field }) => (
									<FormItem>
										<FormLabel>Leverage Factor</FormLabel>
										<FormControl>
											<Input
												placeholder='Leverage'
												{...field}
											/>
										</FormControl>
										<FormMessage />
									</FormItem>
								)}
							/>
							<Button
								type='button'
								onClick={() => remove(index)}
								variant='destructive'
							>
								Remove
							</Button>
						</div>
					))}
					<div className='flex justify-evenly items-center'>
						<Button
							type='button'
							onClick={() => {
								if (fields.length < 5) {
									append({
										ticker: '',
										allocationWeight: '',
										leverageFactor: '',
									});
								}
							}}
						>
							Add Ticker
						</Button>
						<Button type='submit'>Submit</Button>
					</div>
				</form>
			</Form>
		</>
	);
};

export default BacktestForm;
