export default function DashboardLayout({
	children, // will be a page or nested layout
}: {
	children: React.ReactNode;
}) {
	return (
		<div className='w-full border-orange-400 border-2 p-12'>{children}</div>
	);
}
