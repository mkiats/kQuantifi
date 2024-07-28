package com.mkiats.commons.dataAccessObjects;

import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;
import java.util.List;

public interface TickerDao {
	public boolean addTicker(Ticker ticker);

	public void addTickerPrice(TickerPrice tickerPrice);

	public List<TickerPrice> getTickerPrice(
		String tickerName,
		String timeframe
	);
}
