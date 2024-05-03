package com.mkiats.dataAccessObjects;

import com.mkiats.entities.Ticker;

public interface TickerServiceDao {
	public Ticker getTickerBySymbol(String theTickerSymbol);
}
