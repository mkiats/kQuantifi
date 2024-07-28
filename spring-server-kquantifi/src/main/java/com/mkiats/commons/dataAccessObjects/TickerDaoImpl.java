package com.mkiats.commons.dataAccessObjects;

import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;
import com.mkiats.commons.repository.TickerPriceRepository;
import com.mkiats.commons.repository.TickerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TickerDaoImpl implements TickerDao {

	// Constructor injection via @RequiredArgsConstructor
	private final TickerRepository tickerRepository;
	// Constructor injection via @RequiredArgsConstructor
	private final TickerPriceRepository tickerPriceRepository;

	public boolean addTicker(Ticker ticker) {
		if (!(tickerRepository.existsByTickerName(ticker.getTickerName()))) {
			tickerRepository.save(ticker);
			return false;
		}
		return true;
	}

	public void addTickerPrice(TickerPrice tickerPrice) {
		tickerPriceRepository.save(tickerPrice);
	}
}
