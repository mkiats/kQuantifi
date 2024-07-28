package com.mkiats.commons.repository;

import com.mkiats.commons.entities.TickerPrice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerPriceRepository
	extends JpaRepository<TickerPrice, String> {
	public List<TickerPrice> findByTickerNameAndTimeframe(
		String tickerName,
		String timeframe
	);
}
