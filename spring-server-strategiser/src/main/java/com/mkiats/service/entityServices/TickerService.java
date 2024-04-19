package com.mkiats.service.entityServices;

import com.mkiats.entity.Ticker;

public interface TickerService {
    public Ticker getTickerBySymbol(String theTickerSymbol);
    }