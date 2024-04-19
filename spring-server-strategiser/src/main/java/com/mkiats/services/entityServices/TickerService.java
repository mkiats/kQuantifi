package com.mkiats.services.entityServices;

import com.mkiats.entities.Ticker;

public interface TickerService {
    public Ticker getTickerBySymbol(String theTickerSymbol);
    }