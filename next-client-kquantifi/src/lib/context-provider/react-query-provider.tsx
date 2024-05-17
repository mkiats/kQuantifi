'use client';

import {
	QueryClient,
	QueryClientProvider,
	QueryClientProviderProps,
} from '@tanstack/react-query';

const queryClient = new QueryClient({
	defaultOptions: {
		queries: {
			staleTime: Infinity,
		},
	},
});

export function QueryProvider({
	children,
	...props
}: QueryClientProviderProps) {
	return (
		<QueryClientProvider client={queryClient} {...props}>
			{children}
		</QueryClientProvider>
	);
}
