package com.mkiats.commons.dataAccessObjects;

import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;

public interface TickerDao {
	public boolean addTicker(Ticker ticker);

	public void addTickerPrice(TickerPrice tickerPrice);
}
