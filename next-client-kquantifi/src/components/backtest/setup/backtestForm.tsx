'use client';

import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
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
import { cn } from '@/lib/utils';
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue,
} from '@/components/ui/select';

const frequencyEnum = ['weekly', 'monthly'] as const;
const benchmarkEnum = ['SPY', 'Compound'] as const;
const desiredStrategyEnum = ['DollarCostAverage', 'ValueAverage'] as const;
const formSchema = z.object({
	tickerName: z.string().min(2),
	periodicAmount: z.number().int().positive().multipleOf(100).finite().safe(),
	leverageFactor: z.coerce
		.number({ invalid_type_error: 'invalid leverageFactor specified' })
		.int()
		.positive()
		.safe()
		.gte(1)
		.lte(10)
		.optional(),
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
	desiredStrategy: z.enum(desiredStrategyEnum, {
		invalid_type_error: 'invalid benchmark specified',
	}),
	benchmark: z
		.enum(benchmarkEnum, {
			invalid_type_error: 'invalid benchmark specified',
		})
		.optional(),
});

const BacktestForm = ({ handleSubmit }) => {
	const form = useForm<z.infer<typeof formSchema>>({
		mode: 'onSubmit',
		resolver: zodResolver(formSchema),
		defaultValues: {
			tickerName: 'SPY',
			periodicAmount: 100,
			leverageFactor: 1,
			frequency: 'weekly',
			startDate: new Date('2000-01-01'),
			endDate: new Date('2999-01-01'),
			desiredStrategy: 'DollarCostAverage',
			benchmark: 'SPY',
		},
	});

	function onSubmit(values: z.infer<typeof formSchema>) {
		handleSubmit(values);
	}

	return (
		<Form {...form}>
			<form
				onSubmit={form.handleSubmit(onSubmit)}
				className='grid gap-4 justify-items-center content-start grid-rows-5 grid-cols-2 lg:grid-rows-3 lg:grid-cols-3 '
			>
				<FormField
					control={form.control}
					name='tickerName'
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
					control={form.control}
					name='periodicAmount'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Amount to DCA</FormLabel>
							<FormControl>
								<Input type='number' {...field} />
							</FormControl>
							<FormDescription></FormDescription>
							<FormMessage />
						</FormItem>
					)}
				/>
				<FormField
					control={form.control}
					name='leverageFactor'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Leverage factor</FormLabel>
							<FormControl>
								<Input type='number' {...field} />
							</FormControl>
							<FormDescription></FormDescription>
							<FormMessage />
						</FormItem>
					)}
				/>
				<FormField
					control={form.control}
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
					control={form.control}
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
					control={form.control}
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
													{format(field.value, 'PPP')}
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
				<FormField
					control={form.control}
					name='desiredStrategy'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Desired Strategy</FormLabel>
							<Select
								onValueChange={field.onChange}
								defaultValue={field.value}
							>
								<FormControl>
									<SelectTrigger className='w-[280px]'>
										<SelectValue placeholder='Default benchmark: DollarCostAverage' />
									</SelectTrigger>
								</FormControl>
								<SelectContent>
									{desiredStrategyEnum.map(
										(desiredStrategyEnum) => (
											<SelectItem
												key={desiredStrategyEnum}
												value={desiredStrategyEnum}
											>
												{desiredStrategyEnum}
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
					control={form.control}
					name='benchmark'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Benchmark</FormLabel>
							<Select
								onValueChange={field.onChange}
								defaultValue={field.value}
							>
								<FormControl>
									<SelectTrigger className='w-[280px]'>
										<SelectValue placeholder='Default benchmark: SPX' />
									</SelectTrigger>
								</FormControl>
								<SelectContent>
									{benchmarkEnum.map((benchmarkElem) => (
										<SelectItem
											key={benchmarkElem}
											value={benchmarkElem}
										>
											{benchmarkElem}
										</SelectItem>
									))}
								</SelectContent>
							</Select>
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
	);
};

export default BacktestForm;
