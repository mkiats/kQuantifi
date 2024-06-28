import Link from 'next/link';

const navigationLinks = [
	{ url: '/dashboard', title: 'dash' },
	{ url: '/', title: 'about' },
	{ url: '/', title: 'projects' },
	{ url: '/backtest', title: 'Backtest' },
];

const Navbar = () => {
	return (
		<>
			{navigationLinks.map((linkElem) => (
				<Link href={linkElem.url} key={linkElem.title}>
					{linkElem.title}
				</Link>
			))}
		</>
	);
};

export default Navbar;
