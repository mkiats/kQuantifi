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
	PopoverAnchor,
	PopoverContent,
} from '@/components/ui/popover';
import { Calendar, CalendarProps } from '@/components/ui/calendar';
import { Calendar as CalendarIcon } from 'lucide-react';
import { format } from 'date-fns';
import { cn } from '@/lib/utils';
import { DatePickerDemo } from '../ui/datepicker';

const formSchema = z.object({
	tickerName: z.string().min(2),
	dcaAmount: z.number().int().positive().multipleOf(100).finite().safe(),
	leverageFactor: z.coerce
		.number({ invalid_type_error: 'invalid leverageFactor specified' })
		.int()
		.positive()
		.safe()
		.gte(1)
		.lte(10)
		.optional(),
	timeframe: z.enum(['weekly', 'monthly'], {
		required_error: 'timeframe not specified',
	}),
	startDate: z.coerce.date({
		required_error: 'startDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
	endDate: z.coerce.date({
		required_error: 'endDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
	benchmark: z
		.enum(['SPX', 'RegularCompounding'], {
			invalid_type_error: 'invalid benchmark specified',
		})
		.optional(),
	benchmarkGrowthRate: z.coerce
		.number({ invalid_type_error: 'invalid growthRate specified' })
		.int()
		.positive()
		.safe()
		.gte(1)
		.lte(50)
		.optional(),
});

const TickerForm = () => {
	const form = useForm<z.infer<typeof formSchema>>({
		mode: 'onSubmit',
		resolver: zodResolver(formSchema),
		defaultValues: {
			tickerName: 'SPY',
			dcaAmount: 100,
			leverageFactor: 1,
			timeframe: 'weekly',
			startDate: new Date('2000-01-01'),
			endDate: new Date('2999-01-01'),
			benchmark: 'SPX',
			benchmarkGrowthRate: 10,
		},
	});

	function onSubmit(values: z.infer<typeof formSchema>) {
		// Do something with the form values.
		// ✅ This will be type-safe and validated.
		console.log(values);
	}

	return (
		<Form {...form}>
			<form
				onSubmit={form.handleSubmit(onSubmit)}
				className='grid grid-rows-3 grid-cols-3 gap-4 justify-items-center content-start'
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
					name='dcaAmount'
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
					name='timeframe'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Timeframe</FormLabel>
							<FormControl>
								<Input placeholder='weekly' {...field} />
							</FormControl>
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
							<FormControl>
								<Popover>
									<PopoverTrigger asChild>
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
							</FormControl>
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
							<FormControl>
								<Popover>
									<PopoverTrigger asChild>
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
							</FormControl>
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
					name='benchmarkGrowthRate'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Benchmark growth rate</FormLabel>
							<FormControl>
								<Input type='number' {...field} />
							</FormControl>
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

export default TickerForm;
