package com.mkiats.commons.repository;

import com.mkiats.commons.entities.TickerPrice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerPriceRepository
	extends JpaRepository<TickerPrice, String> {
	public TickerPrice findByTickerNameAndTimeframeAndTimestamp(
		String tickerName,
		String timeframe,
		String timestamp
	);

	@Query(
		"SELECT tp.timestamp FROM TickerPrice tp WHERE tp.tickerName = :tickerName AND tp.timeframe = :timeframe"
	)
	List<String> findTimestampsByTickerNameAndTimeframe(
		String tickerName,
		String timeframe
	);
}
