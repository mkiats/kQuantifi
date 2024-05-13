package com.mkiats.commons.dataAccessObjects;

import com.mkiats.commons.entities.Ticker;

public interface TickerServiceDao {
	public Ticker getTickerBySymbol(String theTickerSymbol);
}
