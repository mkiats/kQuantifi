import { Inter } from 'next/font/google';
import React from 'react';
import './globals.css';
import Navbar from '@/components/navbar';

const inter = Inter({ subsets: ['latin'] });

export const metadata = {
	title: 'Create Next App',
	description: 'Generated by create next app',
};

export default function RootLayout({ children }) {
	return (
		<html lang='en'>
			<body className={`${inter.className} bg-color60 text-color30`}>
				<container>
					<header className='w-screen h-24 bg-transparent flex justify-center items-center gap-4 border-b-2 border-b-color30'>
						<Navbar />
					</header>
					<section className='w-screen h-[calc(100vh-6rem) bg-transparent overflow-y-auto'>
						{children}
					</section>
					<footer></footer>
				</container>
			</body>
		</html>
	);
}
