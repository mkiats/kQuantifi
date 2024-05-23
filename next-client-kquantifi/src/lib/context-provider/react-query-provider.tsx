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
	client,
	children,
}: QueryClientProviderProps) {
	return (
		<QueryClientProvider client={queryClient}>
			{children}
		</QueryClientProvider>
	);
}
