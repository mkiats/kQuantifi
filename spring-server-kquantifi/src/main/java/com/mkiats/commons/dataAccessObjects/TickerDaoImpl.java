package com.mkiats.commons.dataAccessObjects;

import com.mkiats.commons.entities.Ticker;
import com.mkiats.commons.entities.TickerPrice;
import com.mkiats.commons.repository.TickerPriceRepository;
import com.mkiats.commons.repository.TickerRepository;
import jakarta.transaction.Transactional;
import java.util.List;
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

	@Override
	public boolean existTicker(String tickerName) {
		return (tickerRepository.existsByTickerName(tickerName));
	}

	@Override
	public boolean addTicker(Ticker ticker) {
		if (!(tickerRepository.existsByTickerName(ticker.getTickerName()))) {
			tickerRepository.save(ticker);
			return false;
		}
		return true;
	}

	@Override
	public void addTickerPrice(TickerPrice tickerPrice) {
		tickerPriceRepository.save(tickerPrice);
	}

	@Override
	public TickerPrice getTickerPrice(
		String tickerName,
		String timeframe,
		String timeStamp
	) {
		return tickerPriceRepository.findByTickerNameAndTimeframeAndTimestamp(
			tickerName,
			timeframe,
			timeStamp
		);
	}

	@Override
	public Ticker getTicker(String tickerName) {
		return tickerRepository.findByTickerName(tickerName);
	}
}
