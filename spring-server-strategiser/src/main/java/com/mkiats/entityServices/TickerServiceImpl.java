package com.mkiats.entityServices;

import com.mkiats.dataAccessObjects.TickerRepository;
import com.mkiats.entities.Ticker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TickerServiceImpl implements TickerService {

	private final TickerRepository tickerRepo;

	public Ticker getTickerBySymbol(String theTickerSymbol) {
		return tickerRepo
			.findByTickerSymbol(theTickerSymbol)
			.orElseThrow(
				() -> new RuntimeException("Ticker symbol cannot be found")
			);
	}
}
