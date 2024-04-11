const mockStockData = [];

for (let i = 0; i < 1000; i++) {
	const start = 1642425322;
	const theObject = { value: i * 5, time: start + i * 86400 };
	mockStockData.push(theObject);
}

export const newMockStockData = mockStockData;
