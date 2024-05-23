'use client';

import * as React from 'react';
import { ThemeProvider as NextThemesProvider } from 'next-themes';
import { type ThemeProviderProps } from 'next-themes/dist/types';



export function ThemeProvider({ children, ...props }: {children: React.ReactNode, props: ThemeProviderProps}) {
	return (
		<NextThemesProvider
			attribute='class'
			defaultTheme='dark'
			enableSystem
			disableTransitionOnChange
			{...props}
		>
			{children}
		</NextThemesProvider>
	);
}
