package com.mkiats.dataAccessObjects;

import com.mkiats.entities.Ticker;
import com.mkiats.repository.TickerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TickerServiceDaoImpl implements TickerServiceDao {

	private final TickerRepository tickerRepo;

	public Ticker getTickerBySymbol(String theTickerSymbol) {
		return tickerRepo
			.findByTickerSymbol(theTickerSymbol)
			.orElseThrow(
				() -> new RuntimeException("Ticker symbol cannot be found")
			);
	}
}
