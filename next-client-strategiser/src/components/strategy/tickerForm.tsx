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

const formSchema = z.object({
	tickerName: z.string().min(2),
	dcaAmount: z.number().int().positive().multipleOf(100).finite().safe(),
	timeframe: z.enum(['weekly', 'monthly'], {required_error: 'timeframe not specified'}),
	startDate: z.coerce.date({
		required_error: 'startDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
	endDate: z.coerce.date({
		required_error: 'endDate not specified',
		invalid_type_error: 'invalid date specified',
	}),
	leverageFactor: z.number({invalid_type_error: 'invalid leverageFactor specified'}).int().positive().safe().gte(1).lte(10).optional(),
	benchmark: z.enum(['SPX', 'RegularCompounding'], {invalid_type_error: 'invalid benchmark specified'}).optional(),
	benchmarkGrowthRate: z.number({invalid_type_error: 'invalid growthRate specified'}).int().positive().safe().gte(1).lte(50).optional(),
});

const TickerForm = () => {
	const form = useForm<z.infer<typeof formSchema>>({
		resolver: zodResolver(formSchema),
		defaultValues: {
			tickerName: '',
		},
	});

	function onSubmit(values: z.infer<typeof formSchema>) {
		// Do something with the form values.
		// ✅ This will be type-safe and validated.
		console.log(values);
	}

	return (
		<Form {...form}>
			<form onSubmit={form.handleSubmit(onSubmit)} className='space-y-8'>
				<FormField
					control={form.control}
					name='tickerName'
					render={({ field }) => (
						<FormItem>
							<FormLabel>Ticker Name</FormLabel>
							<FormControl>
								<Input placeholder='SPX' {...field} />
							</FormControl>
							<FormDescription>
								Ticker name must be listed in US.
							</FormDescription>
							<FormMessage />
						</FormItem>
					)}
				/>
				<Button type='submit'>Submit</Button>
			</form>
		</Form>
	);
};

export default TickerForm;
