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

const benchmarkEnum = ['SPX', 'RegularCompounding'] as const;
const formSchema = z.object({
	benchmark: z
		.enum(benchmarkEnum, {
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

const BenchmarkForm = ({ handleSubmit }) => {
	const form = useForm<z.infer<typeof formSchema>>({
		mode: 'onSubmit',
		resolver: zodResolver(formSchema),
		defaultValues: {
			benchmark: 'SPX',
			benchmarkGrowthRate: 10,
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

export default BenchmarkForm;
