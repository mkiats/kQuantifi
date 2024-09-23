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
import { Settings } from 'http2';

const frequencyEnum = ['weekly', 'monthly'] as const;
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
	initialBalance: z.number().int().positive().multipleOf(100).finite().safe(),
	periodicCashflow: z
		.number()
		.int()
		.positive()
		.multipleOf(100)
		.finite()
		.safe(),
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
	tickerList: z
		.array(z.string())
		.nonempty({ message: 'tickerList must not be empty' }),
	allocationWeightList: z
		.array(z.string())
		.nonempty({ message: 'allocationWeightList must not be empty' }),
	leverageFactor: z
		.array(z.string())
		.nonempty({ message: 'leverageFactor must not be empty' }),
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

	const settingsForm = useForm<z.infer<typeof settingsSchema>>({
		mode: 'onSubmit',
		resolver: zodResolver(settingsSchema),
		defaultValues: {
			portfolioName: 'RagsToRiches',
			investmentStrategy: 'DollarCostAverage',
			rebalanceStrategy: 'RelativeBands',
			initialBalance: 1000,
			periodicCashflow: 1000,
			frequency: 'weekly',
			startDate: new Date('2000-01-01'),
			endDate: new Date(Date.now()),
		},
	});

	function settingsFormSubmitHandler(values: SettingsFormData) {
		setSettings(values);
	}
	function tickersFormSubmitHandler(values: TickersFormData) {
		setTickers(values);
		if (settings && tickers) {
			submitHandler(settings, tickers);
		}
	}

	return (
		<>
			<Form {...settingsForm}>
				<form
					onSubmit={settingsForm.handleSubmit(
						settingsFormSubmitHandler,
					)}
					className='grid gap-4 justify-items-center content-start grid-rows-5 grid-cols-2 lg:grid-rows-3 lg:grid-cols-3 '
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
		</>
	);
};

export default BacktestForm;
