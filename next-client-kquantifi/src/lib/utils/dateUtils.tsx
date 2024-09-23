export const getCurrentUnixTimestamp = (): string => {
    const currentTimestamp = Math.floor(Date.now() / 1000); // Get Unix timestamp in seconds
    return currentTimestamp.toString(); // Convert to string format
  };