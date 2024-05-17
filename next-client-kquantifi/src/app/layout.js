import { Inter } from 'next/font/google';
import React from 'react';
import './globals.css';
import Navbar from '@/components/navbar';
import { ThemeProvider } from '@/lib/context-provider/theme-provider';
import { QueryProvider } from '@/lib/context-provider/react-query-provider';

const inter = Inter({ subsets: ['latin'] });

export const metadata = {
	title: 'kQuantifi',
	description: 'Backtest LETFs',
};

export default function RootLayout({ children }) {
	return (
		<html lang='en' suppressHydrationWarning={true}>
			<body
				className={`${inter.className} bg-background text-foreground`}
			>
				<ThemeProvider>
					<QueryProvider>
						<header className='w-screen h-24 bg-transparent flex justify-center items-center gap-4'>
							<Navbar />
						</header>
						<section className='w-screen h-[calc(100vh-6rem)] overflow-y-auto'>
							{children}
						</section>
						<footer></footer>
					</QueryProvider>
				</ThemeProvider>
			</body>
		</html>
	);
}
