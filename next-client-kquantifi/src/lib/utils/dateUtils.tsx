export const getCurrentUnixTimestamp = (): string => {
	const currentTimestamp = Math.floor(Date.now() / 1000); // Get Unix timestamp in seconds
	return currentTimestamp.toString(); // Convert to string format
};

export const formatDateString = (dateString: string): string => {
	const date = new Date(dateString);
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
	const day = String(date.getDate()).padStart(2, '0');

	return `${year}-${month}-${day}`;
};
