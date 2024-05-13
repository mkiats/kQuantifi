package com.mkiats.commons.repository;

import com.mkiats.commons.entities.Ticker;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, String> {
	Optional<Ticker> findByTickerSymbol(String theTickerSymbol);
}
